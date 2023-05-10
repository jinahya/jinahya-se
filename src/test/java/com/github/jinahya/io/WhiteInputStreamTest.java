package com.github.jinahya.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class WhiteInputStreamTest {

    @Test
    void read_Between0255_() throws IOException {
        for (int i = 0; i < 1024; i++) {
            assertThat(WhiteInputStream.getInstance().read()).isBetween(0, 255);
        }
    }

    @DisplayName("read(byte[])")
    @Test
    void __ReadWithArray() throws IOException {
        final var spy = spy(WhiteInputStream.getInstance());
        final var bytes = spy.read(new byte[1]);
        verify(spy, atLeast(1)).read();
    }

    @DisplayName("read(byte[], int, int)")
    @Test
    void __ReadWithArrayOffAndLen() throws IOException {
        final var spy = spy(WhiteInputStream.getInstance());
        final var bytes = spy.read(new byte[1], 0, 1);
        verify(spy, atLeast(1)).read();
    }
}
