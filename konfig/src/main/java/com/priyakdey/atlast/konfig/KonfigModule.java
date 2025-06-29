package com.priyakdey.atlast.konfig;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.priyakdey.atlast.konfig.core.BackendConfig;
import com.priyakdey.atlast.konfig.core.ConfigStore;
import com.priyakdey.atlast.konfig.parser.ConfigParser;
import com.priyakdey.atlast.konfig.parser.PropertiesConfigParser;
import com.priyakdey.atlast.konfig.parser.YAMLConfigParser;
import com.priyakdey.atlast.konfig.repository.ConfigRepository;
import com.priyakdey.atlast.konfig.repository.ConfigRepositoryFactory;
import com.priyakdey.atlast.konfig.repository.backend.FileBackend;

/**
 * @author Priyak Dey
 */
public class KonfigModule extends AbstractModule {

    private final BackendConfig backendConfig;

    public KonfigModule(BackendConfig backendConfig) {
        this.backendConfig = backendConfig;
    }

    @Override
    protected void configure() {
        // file parsers
        Multibinder<ConfigParser> parsers = Multibinder.newSetBinder(binder(), ConfigParser.class);
        parsers.addBinding().to(PropertiesConfigParser.class);
        parsers.addBinding().to(YAMLConfigParser.class);

        // konfig config object
        bind(BackendConfig.class).toInstance(backendConfig);

        // supported backends
        bind(FileBackend.class);

        bind(ConfigRepositoryFactory.class).in(Scopes.SINGLETON);

        // in-mem config store
        bind(ConfigStore.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public ConfigRepository provideConfigRepository(ConfigRepositoryFactory factory) {
        return factory.create(backendConfig);
    }
}
