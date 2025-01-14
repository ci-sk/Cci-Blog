package org.example.filter;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.utils.Const;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 流量限制过滤器
 * 该过滤器用于限制客户端的请求频率，防止滥用和恶意攻击
 */

@Component
@Order(Const.ORDER_LIMIT)
public class FlowLimitFilter extends HttpFilter {

    @Resource
    StringRedisTemplate template;

    /**
     * 处理HTTP请求的过滤方法
     *
     * @param request  HttpServletRequest对象，包含客户端的请求信息
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @param chain    FilterChain对象，用于将请求传递给下一个过滤器或目标资源
     * @throws IOException      如果在处理请求或响应时发生I/O错误
     * @throws ServletException 如果在处理请求或响应时发生Servlet错误
     */

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        String address = request.getRemoteAddr();
        if(this.tryCount(address)){
        chain.doFilter(request,response);
        }else {
                this.writeBlockMessage(response);
        }
    }

    /**
     * 向客户端发送被阻止的消息
     *
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @throws IOException 如果在处理响应时发生I/O错误
     */

    private void writeBlockMessage(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.forbidden("访问太频繁，请稍后再试").asJsonString());
    }

    /**
     * 尝试对指定的IP地址进行计数
     *
     * @param ip 要计数的IP地址
     * @return 如果计数成功，返回true；如果计数失败，返回false
     */

    private boolean tryCount(String ip){
        synchronized (ip.intern()){
            if(Boolean.TRUE.equals(template.hasKey(Const.FLOW_LIMIT_BLOCK + ip)))
                return false;
            return  this.limitPeriodCheck(ip);
        }
    }

    /**
     * 检查指定IP地址的计数周期
     *
     * @param ip 要检查的IP地址
     * @return 如果计数周期内的请求次数未超过限制，返回true；否则返回false
     */

    private boolean limitPeriodCheck(String ip){
//        System.out.println(ip);
        if(Boolean.TRUE.equals(template.hasKey(Const.FLOW_LIMIT_COUNTER + ip))){
            Long increment = Optional.ofNullable(template.opsForValue().increment(Const.FLOW_LIMIT_COUNTER + ip)).orElse(0L);
            if(increment>25){
                template.opsForValue().set(Const.FLOW_LIMIT_BLOCK+ip,"1",30,TimeUnit.SECONDS);
                return false;
            }
        }else{
            template.opsForValue().set(Const.FLOW_LIMIT_COUNTER+ip,"1",3, TimeUnit.SECONDS);
        }
        return true;
    }
}
