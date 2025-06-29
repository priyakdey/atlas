package com.priyakdey.atlast.konfig.repository;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.priyakdey.atlast.konfig.repository.backend.FileBackend;

/**
 * @author Priyak Dey
 */
public class ConfigRepositoryFactory {

    private final Provider<FileBackend> fileProvider;

    @Inject
    public ConfigRepositoryFactory(Provider<FileBackend> fileProvider) {
        this.fileProvider = fileProvider;
    }

    public ConfigRepository create(BackendConfig config) {
        String type = config.getType();

        return switch (type) {
            case "file" -> fileProvider.get();
            default -> throw new IllegalArgumentException("Unsupported backend type: " +
                    type + ". Supported types: file");
        };

    }

}
