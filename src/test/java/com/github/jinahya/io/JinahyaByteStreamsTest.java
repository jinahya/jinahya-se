package com.github.jinahya.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

class JinahyaByteStreamsTest {

    @Nested
    class CopyTest {

        @DisplayName("(Ljava.io.InputStream;Ljava.io.OutputStream[B)J")
        @Test
        void inputOutputBuffer__() throws IOException {
            // ---------------------------------------------------------------------------------------------------- GIVEN
            final var length = ThreadLocalRandom.current().nextInt(1024);
            final var input = new ByteArrayInputStream(new byte[length]);
            final var output = spy(OutputStream.class);
            final var buffer = new byte[ThreadLocalRandom.current().nextInt(128) + 1];
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var copied = JinahyaByteStreams.copy(input, output, buffer);
            // ---------------------------------------------------------------------------------------------------- THEN
            assertThat(copied).isEqualTo(length);
        }

        @DisplayName("(Ljava.io.InputStream;Ljava.io.OutputStream[BJ)J")
        @Test
        void inputOutputBufferCount__() throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var length = ThreadLocalRandom.current().nextInt(1024);
            final var input = new ByteArrayInputStream(new byte[length]);
            final var output = spy(OutputStream.class);
            final var buffer = new byte[ThreadLocalRandom.current().nextInt(128) + 1];
            final var count = switch (ThreadLocalRandom.current().nextInt(4)) {
                case 0 -> -1;
                case 1 -> 0;
                case 2 -> ThreadLocalRandom.current().nextLong(length + 1);
                default -> ThreadLocalRandom.current().nextLong() & Long.MAX_VALUE;
            };
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var copied = JinahyaByteStreams.copy(input, output, buffer, count);
            // ---------------------------------------------------------------------------------------------------- THEN
            assertThat(copied).isNotNegative();
            if (count == -1) {
                assertThat(copied).isEqualTo(length);
            } else if (count == 0) {
                assertThat(copied).isZero();
            } else {
                assertThat(copied).isLessThanOrEqualTo(count);
            }
        }

        @DisplayName("(Ljava.io.File;Ljava.io.OutputStream[BJ)J")
        @Test
        void fileOutputBufferCount__(@TempDir final File tempDir) throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var file = File.createTempFile("tmp", "tmp", tempDir);
            try (var output = new FileOutputStream(file)) {
                output.write(new byte[ThreadLocalRandom.current().nextInt(1024)]);
                output.flush();
            }
            final var output = spy(OutputStream.class);
            final var buffer = new byte[ThreadLocalRandom.current().nextInt(128) + 1];
            final var count = switch (ThreadLocalRandom.current().nextInt(4)) {
                case 0 -> -1;
                case 1 -> 0;
                case 2 -> ThreadLocalRandom.current().nextLong(file.length() + 1);
                default -> ThreadLocalRandom.current().nextLong() & Long.MAX_VALUE;
            };
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var copied = JinahyaByteStreams.copy(file, output, buffer, count);
            // ---------------------------------------------------------------------------------------------------- THEN
            if (count == -1) {
                assertThat(copied).isEqualTo(file.length());
            } else if (count == 0) {
                assertThat(copied).isZero();
            } else {
                assertThat(copied).isLessThanOrEqualTo(count);
            }
        }

        @DisplayName("(Ljava.io.InputStream;java.io.File;[BJ)J")
        @Test
        void inputFileBufferCount__(@TempDir final File tempDir) throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var length = ThreadLocalRandom.current().nextInt(1024);
            final var input = new ByteArrayInputStream(new byte[length]);
            final var file = File.createTempFile("tmp", "tmp", tempDir);
            final var buffer = new byte[ThreadLocalRandom.current().nextInt(128) + 1];
            final var count = switch (ThreadLocalRandom.current().nextInt(4)) {
                case 0 -> -1;
                case 1 -> 0;
                case 2 -> ThreadLocalRandom.current().nextLong(length + 1);
                default -> ThreadLocalRandom.current().nextLong() & Long.MAX_VALUE;
            };
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var copied = JinahyaByteStreams.copy(input, file, buffer, count);
            // ---------------------------------------------------------------------------------------------------- THEN
            if (count == -1) {
                assertThat(copied).isEqualTo(length);
                assertThat(file).hasSize(copied);
            } else if (count == 0) {
                assertThat(copied).isZero();
                assertThat(file).isEmpty();
            } else {
                assertThat(copied).isLessThanOrEqualTo(count);
            }
        }

        @DisplayName("(Ljava.io.File;Ljava.io.File[BJ)J")
        @Test
        void sourceDestinationBufferCount__(@TempDir final File tempDir) throws IOException {
            // --------------------------------------------------------------------------------------------------- GIVEN
            final var source = File.createTempFile("tmp", "tmp", tempDir);
            try (var output = new FileOutputStream(source)) {
                output.write(new byte[ThreadLocalRandom.current().nextInt(1024)]);
                output.flush();
            }
            final var destination = File.createTempFile("tmp", "tmp", tempDir);
            final var buffer = new byte[ThreadLocalRandom.current().nextInt(128) + 1];
            final var count = switch (ThreadLocalRandom.current().nextInt(4)) {
                case 0 -> -1;
                case 1 -> 0;
                case 2 -> ThreadLocalRandom.current().nextLong(source.length() + 1);
                default -> ThreadLocalRandom.current().nextInt() & Integer.MAX_VALUE;
            };
            // ---------------------------------------------------------------------------------------------------- WHEN
            final var copied = JinahyaByteStreams.copy(source, destination, buffer, count);
            // ---------------------------------------------------------------------------------------------------- THEN
            assertThat(copied).isNotNegative();
            if (count == -1) {
                assertThat(copied).isEqualTo(source.length());
                assertThat(destination).hasSize(copied);
            } else if (count == 0) {
                assertThat(copied).isZero();
                assertThat(destination).isEmpty();
            } else {
                assertThat(copied).isLessThanOrEqualTo(count);
            }
        }
    }
}
