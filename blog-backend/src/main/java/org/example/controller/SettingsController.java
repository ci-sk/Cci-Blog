package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Settings;
import org.example.service.SettingsService;
import org.example.service.impl.SettingsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 该类是一个 Spring MVC 控制器，用于处理与应用程序设置相关的 HTTP 请求。
 * 它提供了获取和更新设置的 API 端点。
 */
@RestController
@RequestMapping("/api")
public class SettingsController {

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

        System.out.println(settings);
        boolean success = settingsService.updateSettings(settings);
        if (success) {
            return RestBean.success("设置更新成功");
        } else {
            return RestBean.failure(400, "设置更新失败");
        }
    }
}