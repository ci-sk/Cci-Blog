package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Settings;
import org.example.service.SettingsService;
import org.example.service.impl.SettingsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @ResponseBody
    @GetMapping("/settings")
    public RestBean<Settings> getSettings() {
        Settings settings = settingsService.getSettings();
        return RestBean.success(settings);
    }

    @ResponseBody
    @PostMapping("/UpdateSettings")
    public RestBean<?> updateSettings(HttpServletResponse response, Settings settings) {
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