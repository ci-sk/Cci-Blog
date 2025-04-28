package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.RequestLog;
import org.example.entity.dto.Settings;
import org.example.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类是一个 Spring MVC 控制器，用于处理与应用程序设置相关的 HTTP 请求。
 * 它提供了获取和更新设置的 API 端点。
 */
@RestController
@RequestMapping("/api")
public class SettingsFileController {

    @Autowired
    private SettingsService settingsService;

    /**
     * 获取当前应用程序的设置。
     * @return 一个包含当前设置的 RestBean 对象。
     */
    @ResponseBody
    @GetMapping("/settings")
    public RestBean<Settings> getSettings()
    {
        Settings settings = settingsService.getSettings();
        return RestBean.success(settings);
    }

    /**
     * 更新应用程序的设置。
     *
     * @param response HTTP 响应对象，用于设置响应的内容类型和字符编码。
     * @param settings 包含更新后设置的对象。
     * @return 一个包含更新结果的 RestBean 对象。
     */
    @ResponseBody
    @PostMapping("/UpdateSettings")
    public RestBean<?> updateSettings(HttpServletResponse response,
                                      @ModelAttribute Settings settings)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        boolean success = settingsService.updateSettings(settings);
        if (success) {
            return RestBean.success("设置更新成功");
        } else {
            return RestBean.failure(400, "设置更新失败");
        }
    }


    @ResponseBody
    @GetMapping("/get/requestLog")
    public RestBean<?> getLog() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<RequestLog> requestLogs = new ArrayList<>();
        String filePath = "blog-backend\\src\\main\\resources\\request.txt";
        String context =  new String(Files.readAllBytes(Paths.get(filePath)));

        context = context.substring(0,context.length()-1);
        String[] split = context.split("},");
        for (String s : split) {
            s = s + "}";
            RequestLog requestLog = objectMapper.readValue(s, RequestLog.class);
            requestLogs.add(requestLog);
        }
        return RestBean.success(requestLogs);
    }

    @ResponseBody
    @GetMapping("/clear/log")
    public RestBean<?> clearLog(){
        try {
            String filePath = "blog-backend\\src\\main\\resources\\request.txt";
            Files.write(Paths.get(filePath), new byte[0]);
            return RestBean.success("日志已清空");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}