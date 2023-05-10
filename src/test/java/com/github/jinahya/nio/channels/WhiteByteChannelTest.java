package com.github.jinahya.nio.channels;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class WhiteByteChannelTest {

    @Test
    void read_Zero_NoRemaining() throws IOException {
        final var dst = ByteBuffer.allocate(0);
        assertThat(WhiteByteChannel.getInstance().read(dst)).isZero();
    }

    @Test
    void read__() throws IOException {
        for (var dst = ByteBuffer.allocate(1024); dst.hasRemaining(); ) {
            final var bytes = WhiteByteChannel.getInstance().read(dst);
            assertThat(bytes).isNotNegative();
        }
    }

    @Test
    void isOpen_True_() {
        assertThat(WhiteByteChannel.getInstance().isOpen()).isTrue();
    }

    @Test
    void close_DoesNotThrow_() {
        assertThatCode(() -> WhiteByteChannel.getInstance().close()).doesNotThrowAnyException();
    }
}
