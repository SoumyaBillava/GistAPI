package org.soumya.gistapi.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static Properties testProperties;
    private static final String TEST_PROPERTIES = "src/test/resources/config.properties";
    private static final String KEY_TOKEN = "token";
    private static final String BASE_URL = "baseUri";

    static {
        testProperties = loadProperties();
        testProperties.putAll(System.getProperties());
        System.setProperties(testProperties);
    }


    public static Properties loadProperties() {
        FileReader reader = null;
        Properties properties = new Properties();
        try {
            reader = new FileReader(TEST_PROPERTIES);
            properties.load(reader);
        } catch (IOException e) {
            System.err.println("Exception while loading property file -config.properties - " + e.getMessage());
        }
        return properties;
    }

    public static String getBaseUrl() {
        return getProperty(BASE_URL);
    }

    public static String getToken() {
        return getProperty(KEY_TOKEN);
    }

    private static String getProperty(final String key) {
        String value = testProperties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " system property is not defined");
        }
        return value;
    }
}
