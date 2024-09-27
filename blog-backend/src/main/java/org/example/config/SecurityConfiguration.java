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

@Configuration
public class SecurityConfiguration {
    @Resource
    JwtUtils utils;
    @Resource
    JwtAuthorizeFilter filter;
    @Resource
    AccountService service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(conf -> conf
                            .requestMatchers("/api/auth/**").permitAll()
                            .anyRequest().authenticated()
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
            v.setToken(token);
        });

        response.getWriter().write(RestBean.success(vo).asJsonString());
    }

    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
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

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());

    }

    public void onUnauthorized(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
    }

    public void onAccessDeny(HttpServletRequest request,
                             HttpServletResponse response,
                             AccessDeniedException exception) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(RestBean.forbidden(exception.getMessage()).asJsonString());

    }
}
