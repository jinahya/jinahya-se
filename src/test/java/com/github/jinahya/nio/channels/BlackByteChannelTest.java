package com.github.jinahya.nio.channels;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BlackByteChannelTest {

    @Test
    void write_Zero_NoRemaining() throws IOException {
        final var src = ByteBuffer.allocate(0);
        assertThat(BlackByteChannel.getInstance().write(src)).isZero();
    }

    @Test
    void write__() throws IOException {
        for (var src = ByteBuffer.allocate(1024); src.hasRemaining(); ) {
            final var written = BlackByteChannel.getInstance().write(src);
            assertThat(written).isNotNegative();
        }
    }

    @Test
    void isOpen_True_() {
        assertThat(BlackByteChannel.getInstance().isOpen()).isTrue();
    }

    @Test
    void close_DoesNotThrow_() {
        assertThatCode(() -> BlackByteChannel.getInstance().close()).doesNotThrowAnyException();
    }
}
