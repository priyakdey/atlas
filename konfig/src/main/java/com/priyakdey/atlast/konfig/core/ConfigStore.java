package com.priyakdey.atlast.konfig.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Priyak Dey
 */
public class ConfigStore {

    private final Map<String, Map<String, Map<String, Object>>> store = new ConcurrentHashMap<>();

    public void put(String appName, String env, Map<String, Object> config) {
        if (!store.containsKey(appName)) {
            store.put(appName, new ConcurrentHashMap<>());
        }
        Map<String, Map<String, Object>> envMap = store.get(appName);
        if (!envMap.containsKey(env)) {
            envMap.put(env, new ConcurrentHashMap<>(config));
        }
    }

    public Map<String, Object> get(String appName, String env) {
        if (store.containsKey(appName)) {
            Map<String, Map<String, Object>> envMap = store.get(appName);
            if (envMap.containsKey(env)) {
                return envMap.get(env);
            }
        }

        throw new IllegalArgumentException("Configuration not found for app: " + appName + " in environment: " + env);
    }

}
