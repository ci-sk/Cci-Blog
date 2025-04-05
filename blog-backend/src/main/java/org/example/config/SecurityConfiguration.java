package org.example.config;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Account;
import org.example.entity.vo.response.AuthorizeVO;
import org.example.filter.JwtAuthorizeFilter;
import org.example.service.AccountService;
import org.example.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Spring Security 配置类，用于配置应用的安全策略
 */

@Configuration
public class SecurityConfiguration {
    @Resource
    JwtUtils utils;
    @Resource
    JwtAuthorizeFilter filter;
    @Resource
    AccountService service;

    /**
     * 配置 Spring Security 的过滤器链
     *
     * @param http HttpSecurity 对象，用于配置安全策略
     * @return SecurityFilterChain 对象，代表配置好的过滤器链
     * @throws Exception 如果配置过程中发生错误，抛出异常
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(conf -> conf
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/getAll/**").permitAll()
                            .requestMatchers("/api/getById/**").permitAll()
//                            //其他所有路径必须角色为admin才能访问
                            .anyRequest().hasRole("admin")
//                            .anyRequest().authenticated()
                    )
                    .formLogin(conf -> conf
                            .loginProcessingUrl("/api/auth/login")
                            .failureHandler(this::onAuthenticationFailure)
                            .successHandler(this::onAuthenticationSuccess)
                    )
                    .logout(conf -> conf
                            .logoutUrl("/api/auth/logout")
                            .logoutSuccessHandler(this::onLogoutSuccess))
                    .exceptionHandling(conf -> conf
                            .authenticationEntryPoint(this::onUnauthorized)
                            .accessDeniedHandler(this::onAccessDeny)
                    )
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(conf ->conf
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }


    /**
     * 处理登录成功事件
     *
     * @param request HttpServletRequest 对象，包含请求信息
     * @param response HttpServletResponse 对象，用于响应客户端
     * @param authentication 认证对象，包含已认证的用户信息
     * @throws IOException 如果在处理过程中发生 I/O 错误，抛出异常
     */

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        User user = (User) authentication.getPrincipal();
        Account account =service.findAccountByNameOrEmail(user.getUsername());
        String token = utils.createJwt(user,account.getUid(),user.getUsername());
        AuthorizeVO vo = account.asViewObject(AuthorizeVO.class,v->{
            v.setExpire(utils.expireTime());
            v.setRole(account.getRole());
            v.setToken(token);
            v.setUsername(account.getUsername());
        });
        response.getWriter().write(RestBean.success(vo).asJsonString());
    }

    /**
     * 处理注销成功事件
     *
     * @param request HttpServletRequest 对象，包含请求信息
     * @param response HttpServletResponse 对象，用于响应客户端
     * @param authentication 认证对象，包含已认证的用户信息
     * @throws IOException 如果在处理过程中发生 I/O 错误，抛出异常
     * @throws ServletException 如果在处理过程中发生 Servlet 错误，抛出异常
     */

    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if (utils.invalidateJwt(authorization)){
            writer.write(RestBean.success().asJsonString());
        }else {
            writer.write(RestBean.failure(400,"注销失败").asJsonString());
        }
    }

    /**
     * 处理登录失败事件
     *
     * @param request HttpServletRequest 对象，包含请求信息
     * @param response HttpServletResponse 对象，用于响应客户端
     * @param exception 认证异常对象，包含登录失败的原因
     * @throws IOException 如果在处理过程中发生 I/O 错误，抛出异常
     */

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());

    }

    /**
     * 处理未授权访问事件
     *
     * @param request HttpServletRequest 对象，包含请求信息
     * @param response HttpServletResponse 对象，用于响应客户端
     * @param exception 认证异常对象，包含未授权访问的原因
     * @throws IOException 如果在处理过程中发生 I/O 错误，抛出异常
     */

    public void onUnauthorized(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
    }

    /**
     * 处理访问被拒绝事件
     *
     * @param request HttpServletRequest 对象，包含请求信息
     * @param response HttpServletResponse 对象，用于响应客户端
     * @param exception 访问被拒绝异常对象，包含访问被拒绝的原因
     * @throws IOException 如果在处理过程中发生 I/O 错误，抛出异常
     */

    public void onAccessDeny(HttpServletRequest request,
                             HttpServletResponse response,
                             AccessDeniedException exception) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(RestBean.forbidden(exception.getMessage()).asJsonString());

    }
}
