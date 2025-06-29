package com.priyakdey.atlast.konfig.parser;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author Priyak Dey
 */
public class YAMLConfigParser implements ConfigParser {

    @Override
    public boolean supports(String filename) {
        return filename.endsWith(".yaml") || filename.endsWith(".yml");
    }

    @Override
    public Map<String, Object> parse(InputStream inputStream) {
        LoaderOptions options = new LoaderOptions();
        options.setAllowDuplicateKeys(false);   // 1.2 specification
        Yaml yaml = new Yaml(options);
        return yaml.load(inputStream);
    }
}
