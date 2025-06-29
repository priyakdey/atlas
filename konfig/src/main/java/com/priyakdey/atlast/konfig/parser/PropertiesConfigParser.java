package com.priyakdey.atlast.konfig.parser;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Priyak Dey
 */
public class PropertiesConfigParser implements ConfigParser {

    @Override
    public boolean supports(String filename) {
        return filename.endsWith(".properties");
    }

    @Override
    public Map<String, Object> parse(InputStream inputStream) throws Exception {
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().toString().trim(),
                        e -> e.getValue().toString().trim()));
    }
}
