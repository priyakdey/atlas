package com.priyakdey.atlast.konfig.parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FileBasedConfigSource Tests")
class FileBasedConfigSourceTest {

    private File tempFile;

    @AfterEach
    void cleanup() {
        if (tempFile != null && tempFile.exists()) {
            assertTrue(tempFile.delete(), "Failed to delete temp file: " +
                    tempFile.getAbsolutePath());
        }
    }

    @Test
    void testNameReturnsFileName() throws IOException {
        tempFile = createTempFile("test-config", ".properties", "foo=bar");

        FileBasedConfigSource source = new FileBasedConfigSource(tempFile);
        assertEquals(tempFile.getName(), source.name());
    }

    @Test
    void testStreamReturnsContent() throws Exception {
        String content = "foo=bar";
        tempFile = createTempFile("test-config", ".properties", content);

        FileBasedConfigSource source = new FileBasedConfigSource(tempFile);
        try (InputStream in = source.stream()) {
            String read = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            assertEquals(content, read);
        }
    }

    @Test
    void testToStringReturnsAbsolutePath() throws IOException {
        tempFile = createTempFile("test-config", ".properties", "");
        FileBasedConfigSource source = new FileBasedConfigSource(tempFile);

        String expected = "FileBasedConfigSource{file=" + tempFile.getAbsolutePath() + "}";
        assertEquals(expected, source.toString());
    }

    @Test
    void testStreamThrowsForMissingFile() {
        File nonExistent = new File("non-existent-file-987654.properties");
        FileBasedConfigSource source = new FileBasedConfigSource(nonExistent);

        assertThrows(IOException.class, source::stream);
    }

    private File createTempFile(String prefix, String suffix, String content) throws IOException {
        tempFile = File.createTempFile(prefix, suffix);
        try (Writer writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }

}