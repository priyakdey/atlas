package com.priyakdey.atlast.konfig.parser;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Priyak Dey
 */
public interface ConfigSource {

    String name();

    InputStream stream() throws IOException;

}
