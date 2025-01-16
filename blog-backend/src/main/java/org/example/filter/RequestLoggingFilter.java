package org.example.filter;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.dto.RequestLog;
import org.example.utils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

import static org.example.utils.TimeFormatUtil.formatTimestamp;

@Component
@Order(Const.ORDER_AUTH)
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 记录请求开始时间
        long startTime = System.currentTimeMillis();

        String url = httpRequest.getRequestURL().substring(21);

        // 获取所有请求参数
        Map<String, String[]> parameterMap = httpRequest.getParameterMap();

        // 将请求和响应传递给下一个过滤器或Servlet
        chain.doFilter(request, response);

        // 检查响应状态码是否在200到299之间，表示请求成功
        int code = httpResponse.getStatus();
        //        code >= 200 && code < 300

        // 记录请求结束时间
        long endTime = System.currentTimeMillis();

        // 计算请求处理时间
        long processingTime = endTime - startTime;

        // 拼接请求参数字符串
        StringBuilder parameterString = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            parameterString.append(entry.getKey()).append("=").append(entry.getValue()[0]).append("&");
        }
        if (parameterString.length() > 0) {
            parameterString.setLength(parameterString.length() - 2); // 去掉最后一个逗号和空格
        }

        if (!parameterString.isEmpty()) {
            url+="?"+parameterString;
        }

        // 打印请求的URL和处理时间
        logger.info("请求结果:{},请求的地址: {}, 处理时间: {}ms",code, url, processingTime);


        RequestLog requestLog = new RequestLog();
        requestLog.setUrl(url)
           .setStatus(code)
           .setTime(formatTimestamp(endTime))
           .setProcessingTime(processingTime);
        log(requestLog);

    }

    public void log(RequestLog requestLog)
    {

            File file = new File("blog-backend\\src\\main\\resources\\request.txt");

            // 如果文件存在且大小超过1MB，则清空文件
            if (file.exists() && file.length() > 1024 * 1024) {
                try (FileWriter writer = new FileWriter(file, false)) { // false表示覆盖写入
                    writer.write(""); // 清空文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(requestLog.toJsonString() + ",\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
