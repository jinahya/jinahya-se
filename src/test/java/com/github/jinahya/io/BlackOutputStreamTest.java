package com.github.jinahya.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class BlackOutputStreamTest {

    @Test
    void write_() throws IOException {
        assertThatCode(() -> BlackOutputStream.getInstance().write(ThreadLocalRandom.current().nextInt()))
                .doesNotThrowAnyException();
    }

    @Test
    void write_B() throws IOException {
        final var spy = spy(BlackOutputStream.getInstance());
        final byte[] b = new byte[1];
        spy.write(b);
        verify(spy, atLeast(1)).write(anyInt());
    }

    @Test
    void write_BOffLen() throws IOException {
        final var spy = spy(BlackOutputStream.getInstance());
        final byte[] b = new byte[1];
        spy.write(b, 0, 1);
        verify(spy, atLeast(1)).write(anyInt());
    }
}
