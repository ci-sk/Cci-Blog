package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.dto.Settings;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    private static final String SETTINGS_FILE = "settings.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Settings readSettings() throws IOException {
        Resource resource = new ClassPathResource(SETTINGS_FILE);
        return objectMapper.readValue(resource.getInputStream(), Settings.class);

    }

    public static void writeSettings(Settings settings) throws IOException {
        Resource resource = new ClassPathResource(SETTINGS_FILE);
        File file = new File(resource.getURI());
        objectMapper.writeValue(file, settings);
    }
}