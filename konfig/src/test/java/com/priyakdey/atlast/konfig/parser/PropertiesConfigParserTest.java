package com.priyakdey.atlast.konfig.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PropertiesConfigParser Tests")
class PropertiesConfigParserTest {

    private final PropertiesConfigParser parser = new PropertiesConfigParser();

    @Test
    @DisplayName("Supports .properties files")
    void testSupports() {
        assertTrue(parser.supports("test.properties"));
        assertFalse(parser.supports("test.yaml"));
        assertFalse(parser.supports("config.yml"));
    }

    @Test
    @DisplayName("Parse basic key-value pairs")
    void testBasicKeyValueParsing() throws Exception {
        String input = "foo=bar\nhello=world";
        Map<String, Object> result = parse(input);

        assertEquals(2, result.size());
        assertEquals("bar", result.get("foo"));
        assertEquals("world", result.get("hello"));
    }

    @Test
    @DisplayName("Parse empty file")
    void testEmptyFileReturnsEmptyMap() throws Exception {
        Map<String, Object> result = parse("");
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Parse file with comments and blank lines")
    void testIgnoresCommentsAndBlankLines() throws Exception {
        String input = "# This is a comment\n\nfoo=bar\n! another comment\nhello=world";
        Map<String, Object> result = parse(input);

        assertEquals(2, result.size());
        assertEquals("bar", result.get("foo"));
        assertEquals("world", result.get("hello"));
    }

    @Test
    @DisplayName("Ignore whitespaces around keys and values")
    void testSpacesAreTrimmed() throws Exception {
        String input = "   foo    =   bar   \nkey = value ";
        Map<String, Object> result = parse(input);

        assertEquals("bar", result.get("foo"));
        assertEquals("value", result.get("key"));
    }

    @Test
    @DisplayName("Parse UTF-8 characters")
    void testEscapedUnicodeAndCharacters() throws Exception {
        String input = "unicode=\\u2603\nescaped=hello\\ world";
        Map<String, Object> result = parse(input);

        assertEquals("☃", result.get("unicode"));
        assertEquals("hello world", result.get("escaped"));
    }

    @Test
    @DisplayName("Parse duplicate keys with last one winning")
    void testDuplicateKeysLastOneWins() throws Exception {
        String input = "foo=bar\nfoo=baz";
        Map<String, Object> result = parse(input);

        assertEquals(1, result.size());
        assertEquals("baz", result.get("foo"));
    }

    private Map<String, Object> parse(String content) throws Exception {
        InputStream stream = new ByteArrayInputStream(content.getBytes());
        return parser.parse(stream);
    }

}