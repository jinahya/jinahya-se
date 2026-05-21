package com.github.jinahya.io;

import java.io.File;

public class JinahyaFileUtils {

    public static File deleteOnExit(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null");
        }
        file.deleteOnExit();
        return file;
    }

    private JinahyaFileUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
