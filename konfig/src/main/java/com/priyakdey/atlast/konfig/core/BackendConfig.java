package com.priyakdey.atlast.konfig.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Priyak Dey
 */
public class BackendConfig {

    private static final String BACKEND_TYPE_KEY = "konfig.backend.type";

    public enum Type {FILE, GIT, DB, BLOB}

    private final Type type;
    private final Properties properties;

    public BackendConfig(InputStream inputStream) throws IOException {
        this.properties = new Properties();
        properties.load(inputStream);
        type = mapToType((String) properties.get(BACKEND_TYPE_KEY));
    }

    private Type mapToType(String s) {
        if (s == null) {
            throw new IllegalArgumentException(BACKEND_TYPE_KEY + " is missing in configuration");
        }

        return switch (s) {
            case "file" -> Type.FILE;
            case "git" -> Type.GIT;
            case "db" -> Type.DB;
            case "blob" -> Type.BLOB;
            default -> throw new IllegalArgumentException("Unknown backend type: " + s);
        };
    }

    public Type getType() {
        return type;
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

    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }

}
