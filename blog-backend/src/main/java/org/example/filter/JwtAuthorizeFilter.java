package org.example.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthorizeFilter 类是一个 Spring Security 过滤器，用于处理 JWT（JSON Web Token）认证。
 * 它继承自 OncePerRequestFilter，确保在每个请求上只执行一次过滤操作。
 */
@Component
public class JwtAuthorizeFilter  extends OncePerRequestFilter {

    @Resource
    JwtUtils utils;

    /**
     * 此方法实现了 OncePerRequestFilter 接口的 doFilterInternal 方法。
     * 它的主要功能是从请求头中提取 JWT，解析并验证它，然后设置 Spring Security 上下文。
     * 如果 JWT 有效，它会将用户信息和权限设置到 SecurityContextHolder 中，以便后续的安全检查。
     *
     * @param request  HttpServletRequest 对象，包含客户端的请求信息。
     * @param response HttpServletResponse 对象，用于向客户端发送响应。
     * @param filterChain FilterChain 对象，用于将请求传递给下一个过滤器或目标资源。
     * @throws ServletException 如果在处理请求或响应时发生 Servlet 错误。
     * @throws IOException 如果在处理请求或响应时发生 I/O 错误。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取 Authorization 字段的值
        String authorization = request.getHeader("Authorization");
        // 调用 JwtUtils 类的 resolveJwt 方法解析 JWT
        DecodedJWT jwt = utils.resolveJwt(authorization);
        // 如果 JWT 不为空，则进行后续处理
        if(jwt!=null){
            // 调用 JwtUtils 类的 toUser 方法将 JWT 转换为 UserDetails 对象
            UserDetails user = utils.toUser(jwt);
            // 创建一个 UsernamePasswordAuthenticationToken 对象，用于存储用户信息和权限
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            // 设置认证的详细信息，例如请求的 IP 地址和会话 ID
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将认证信息设置到 SecurityContextHolder 中，以便后续的安全检查
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 将用户 ID 存储在请求属性中，以便后续使用
            request.setAttribute("id",utils.toId(jwt));
        }
        // 将请求和响应传递给过滤器链中的下一个过滤器或目标资源
        filterChain.doFilter(request,response);
    }
}
