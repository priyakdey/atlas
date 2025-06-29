package com.priyakdey.atlast.konfig.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.priyakdey.atlast.konfig.KonfigModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * @author Priyak Dey
 */
public class KonfigBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(KonfigBootstrap.class);

    private static final String DEFAULT_CONFIG_FILE = "konfig-default.properties";

    public static Injector init() {
        try (InputStream is = KonfigBootstrap.class.getClassLoader()
                .getResourceAsStream(DEFAULT_CONFIG_FILE)) {
            if (is == null) {
                throw new IOException("Missing bootstrap file " + DEFAULT_CONFIG_FILE);
            }

            BackendConfig config = new BackendConfig(is);
            return Guice.createInjector(new KonfigModule(config));
        } catch (IOException e) {
            logger.error("Failed to bootstrap atlas-konfig: {}", e.getMessage());
            throw new UncheckedIOException(e);
        }
    }

}
