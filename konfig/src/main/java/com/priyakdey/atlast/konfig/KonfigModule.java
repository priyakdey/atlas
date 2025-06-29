package com.priyakdey.atlast.konfig;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.priyakdey.atlast.konfig.parser.ConfigParser;
import com.priyakdey.atlast.konfig.parser.PropertiesConfigParser;
import com.priyakdey.atlast.konfig.parser.YAMLConfigParser;

/**
 * @author Priyak Dey
 */
public class KonfigModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<ConfigParser> parsers = Multibinder.newSetBinder(binder(), ConfigParser.class);
        parsers.addBinding().to(PropertiesConfigParser.class);
        parsers.addBinding().to(YAMLConfigParser.class);
    }
}
