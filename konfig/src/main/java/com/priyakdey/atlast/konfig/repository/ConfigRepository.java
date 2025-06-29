package com.priyakdey.atlast.konfig.repository;

import com.priyakdey.atlast.konfig.parser.ConfigSource;

import java.util.List;

/**
 * @author Priyak Dey
 */
public interface ConfigRepository {

    default void load() {
        // Default implementation is no-op
    }

    List<ConfigSource> getSources(String appName, String env);
}
