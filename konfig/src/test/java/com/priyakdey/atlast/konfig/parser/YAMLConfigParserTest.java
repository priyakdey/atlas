package com.priyakdey.atlast.konfig.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("YAMLConfigParser Tests")
class YAMLConfigParserTest {

    private final YAMLConfigParser parser = new YAMLConfigParser();

    @Test
    @DisplayName("Supports YAML file extensions")
    void testSupports() {
        assertTrue(parser.supports("config.yaml"));
        assertTrue(parser.supports("settings.yml"));
        assertFalse(parser.supports("app.properties"));
        assertFalse(parser.supports("data.json"));
    }

    @Test
    @DisplayName("Parses flat YAML")
    void testParsesFlatYaml() {
        String yaml = "foo: bar\nbaz: 42\nflag: true";
        Map<String, Object> result = parse(yaml);

        assertEquals("bar", result.get("foo"));
        assertEquals(42, result.get("baz"));
        assertEquals(true, result.get("flag"));
    }

    private Map<String, Object> parse(String content) {
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        return parser.parse(inputStream);
    }

}