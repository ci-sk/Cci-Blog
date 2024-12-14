package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.dto.Settings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    private static final String SETTINGS_FILE = "blog-backend\\src\\main\\resources\\settings.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Settings readSettings() throws IOException {
        return objectMapper.readValue(new File(SETTINGS_FILE), Settings.class);
    }

    public static void writeSettings(Settings settings) throws IOException {
        objectMapper.writeValue(new File(SETTINGS_FILE), settings);
    }
}