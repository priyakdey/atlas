package com.priyakdey.atlast.konfig.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Priyak Dey
 */
public class BackendConfig {

    private final Properties properties;

    public BackendConfig(InputStream inputStream) throws IOException {
        this.properties = new Properties();
        properties.load(inputStream);
    }

    public String getType() {
        return properties.getProperty("konfig.backend.type");
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public Map<String, String> all() {
        return properties.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> e.getValue().toString()
                ));
    }

    public boolean has(String key) {
        return properties.containsKey(key);
    }

}
