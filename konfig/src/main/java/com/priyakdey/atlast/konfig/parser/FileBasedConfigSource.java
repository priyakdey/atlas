package com.priyakdey.atlast.konfig.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Priyak Dey
 */
public record FileBasedConfigSource(File file) implements ConfigSource {

    @Override
    public String name() {
        return file.getName();
    }

    @Override
    public InputStream stream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public String toString() {
        return "FileBasedConfigSource{file=" + file.getAbsolutePath() + "}";
    }
}
