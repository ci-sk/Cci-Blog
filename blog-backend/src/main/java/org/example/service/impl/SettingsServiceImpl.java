package org.example.service.impl;

import org.example.entity.dto.Settings;
import org.example.service.SettingsService;
import org.example.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Override
    public Settings getSettings() {
        try {
            return FileUtil.readSettings();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateSettings(Settings settings) {
        try {
            FileUtil.writeSettings(settings);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}