package com.priyakdey.atlast.konfig.parser;

import com.google.inject.Inject;

import java.util.Set;

/**
 * @author Priyak Dey
 */
public record ParserRegistry(Set<ConfigParser> parsers) {

    @Inject
    public ParserRegistry {
    }

    public ConfigParser getParser(String filename) {
        return parsers.stream()
                .filter(parser -> parser.supports(filename.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Only .properties, .yaml, .json and .toml formats are supported."));
    }

}
