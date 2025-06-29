package com.priyakdey.atlast.konfig.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ParserRegistry Tests")
class ParserRegistryTest {

    @Test
    void testReturnsPropertiesParser() {
        ParserRegistry registry = new ParserRegistry(Set.of(new PropertiesConfigParser(),
                new YAMLConfigParser()));
        ConfigParser parser = registry.getParser("app.properties");

        assertInstanceOf(PropertiesConfigParser.class, parser);
    }

    @Test
    void testReturnsYamlParserForYaml() {
        ParserRegistry registry = new ParserRegistry(Set.of(new PropertiesConfigParser(),
                new YAMLConfigParser()));
        ConfigParser parser = registry.getParser("settings.yaml");

        assertInstanceOf(YAMLConfigParser.class, parser);
    }

    @Test
    void testReturnsYamlParserForYml() {
        ParserRegistry registry = new ParserRegistry(Set.of(new PropertiesConfigParser(),
                new YAMLConfigParser()));
        ConfigParser parser = registry.getParser("settings.yml");

        assertInstanceOf(YAMLConfigParser.class, parser);
    }

    @Test
    void testThrowsOnUnsupportedExtension() {
        ParserRegistry registry = new ParserRegistry(Set.of(new PropertiesConfigParser(),
                new YAMLConfigParser()));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> registry.getParser("config.json"));

        assertTrue(ex.getMessage().contains("Only .properties, .yaml, .json and .toml formats are supported."));
    }

    @Test
    void testCaseInsensitiveMatching() {
        ParserRegistry registry = new ParserRegistry(Set.of(new PropertiesConfigParser(),
                new YAMLConfigParser()));

        ConfigParser parser = registry.getParser("CONFIG.PROPERTIES");
        assertInstanceOf(PropertiesConfigParser.class, parser);
    }
}