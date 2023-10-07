package com.github.jinahya.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatCode;

class JinahyaDataInputStreamTest {

    @DisplayName("readIntLe()S")
    @Test
    void readShortLe_DoesNotThrow_() throws IOException {
        try (var input = new JinahyaDataInputStream(WhiteInputStream.getInstance())) {
            assertThatCode(() -> {
                final short value = input.readShortLe();
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("readIntLe()I")
    @Test
    void readIntLe_DoesNotThrow_() throws IOException {
        try (var input = new JinahyaDataInputStream(WhiteInputStream.getInstance())) {
            assertThatCode(() -> {
                final int value = input.readIntLe();
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("readLongLe()J")
    @Test
    void readLongLe_DoesNotThrow_() throws IOException {
        try (var input = new JinahyaDataInputStream(WhiteInputStream.getInstance())) {
            assertThatCode(() -> {
                final long value = input.readLongLe();
            }).doesNotThrowAnyException();
        }
    }
}
