package com.task1.selenium.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    final public static String path ="src/test/resources/config/config.properties";
    private static ConfigLoader instance;
    private Properties properties;

    private ConfigLoader() {
        properties = new Properties();
        loadConfig(path);
    }

    // Singelton
    public static ConfigLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigLoader.class) {
                if (instance == null) {
                    instance = new ConfigLoader();
                }
            }
        }
        return instance;
    }

    // Download config file .properties
    private void loadConfig(String filePath) {
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(Descriptions.ERROR_DOWNLOAD_FILE.getText() + filePath);
        }
    }

    // get setting
    public String get(String key) {
        return properties.getProperty(key);
    }
}
