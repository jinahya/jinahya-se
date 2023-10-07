package com.github.jinahya.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThatCode;

class JinahyaDataOutputStreamTest {

    @DisplayName("writeShortLe(S)V")
    @Test
    void writeShortLe_DoesNotThrow_() throws IOException {
        try (var output = new JinahyaDataOutputStream(BlackOutputStream.getInstance())) {
            assertThatCode(() -> {
                output.writeShortLe(current().nextInt());
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("writeIntLe(I)V")
    @Test
    void writeIntLe_DoesNotThrow_() throws IOException {
        try (var output = new JinahyaDataOutputStream(BlackOutputStream.getInstance())) {
            assertThatCode(() -> {
                output.writeIntLe(current().nextInt());
            }).doesNotThrowAnyException();
        }
    }

    @DisplayName("writeLongLe(L)V")
    @Test
    void writeLongLe_DoesNotThrow_() throws IOException {
        try (var output = new JinahyaDataOutputStream(BlackOutputStream.getInstance())) {
            assertThatCode(() -> {
                output.writeLongLe(current().nextLong());
            }).doesNotThrowAnyException();
        }
    }
}
