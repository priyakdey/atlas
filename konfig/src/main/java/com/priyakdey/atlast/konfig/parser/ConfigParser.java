package com.priyakdey.atlast.konfig.parser;

import java.io.InputStream;
import java.util.Map;

/**
 * @author Priyak Dey
 */
public interface ConfigParser {

    boolean supports(String filename);

    Map<String, Object> parse(InputStream inputStream) throws Exception;

}
