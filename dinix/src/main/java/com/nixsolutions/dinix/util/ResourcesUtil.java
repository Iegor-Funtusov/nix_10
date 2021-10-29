package com.nixsolutions.dinix.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ResourcesUtil {

    private ResourcesUtil() { }

    public static Map<String, String> getResources(ClassLoader classLoader) {
        try(InputStream inputStream = classLoader.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> map = new HashMap<>();
            for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
                String key = String.valueOf(objectObjectEntry.getKey());
                String value = String.valueOf(objectObjectEntry.getValue());
                map.put(key, value);
            }
            return map;
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            throw new RuntimeException("file not found");
        }
    }
}
