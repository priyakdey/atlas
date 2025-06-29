package com.priyakdey.atlast.konfig.repository.backend;

import com.priyakdey.atlast.konfig.parser.ConfigSource;
import com.priyakdey.atlast.konfig.parser.FileBasedConfigSource;
import com.priyakdey.atlast.konfig.repository.ConfigRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Priyak Dey
 */
public class FileBackend implements ConfigRepository {

    private File configDir;

    public void init(String rootDir) {
        File dir = new File(rootDir);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + rootDir);
        }
        this.configDir = dir;
    }

    @Override
    public List<ConfigSource> getSources(String appName, String env) {
        List<ConfigSource> configSources = new ArrayList<>();

        File appDir = new File(configDir, appName);
        if (appDir.exists() && appDir.isDirectory()) {
            // application specific env specific config file
            addIfExists(appDir, appName + "-" + env + ".", configSources);
            // application specific common env config file
            addIfExists(appDir, appName + ".", configSources);
        }

        // common env specific config file
        addIfExists(configDir, "common-" + env + ".", configSources);

        // common config file
        addIfExists(configDir, "common.", configSources);

        return configSources;
    }

    private void addIfExists(File dir, String filename, List<ConfigSource> configSources) {
        File[] files = dir.listFiles((_dir, name) -> {
            if (name.startsWith(filename)) {
                File file = new File(_dir, name);
                return file.isFile() && file.canRead();
            }

            return false;
        });

        if (Objects.requireNonNull(files).length > 1) {
            // TODO: replace with a custom exception
            throw new IllegalStateException("At most 1 files for a specific environment.");
        }

        for (File file : files) {
            configSources.add(new FileBasedConfigSource(file));
        }
    }

}
