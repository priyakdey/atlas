package com.priyakdey.atlast.konfig.repository;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.priyakdey.atlast.konfig.core.BackendConfig;
import com.priyakdey.atlast.konfig.repository.backend.FileBackend;

/**
 * @author Priyak Dey
 */
public record ConfigRepositoryFactory(Provider<FileBackend> fileProvider) {

    @Inject
    public ConfigRepositoryFactory {
    }

    public ConfigRepository create(BackendConfig config) {
        BackendConfig.Type type = config.getType();

        return switch (type) {
            case FILE -> fileProvider.get();
            case DB -> throw new IllegalArgumentException("db backend not implemented yet");
            case GIT -> throw new IllegalArgumentException("git backend not implemented yet");
            case BLOB -> throw new IllegalArgumentException("blob backend not implemented yet");
        };
    }

}
