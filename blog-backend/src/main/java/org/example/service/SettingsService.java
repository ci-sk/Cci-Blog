package org.example.service;

import org.example.entity.dto.Settings;

public interface SettingsService {
    Settings getSettings();

    boolean updateSettings(Settings settings);
}