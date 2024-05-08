package com.enuygun.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigHelper {
    public static Properties readProp(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(new InputStreamReader(fis, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties file: " + e.getMessage(), e);
        }
        return properties;
    }
}
