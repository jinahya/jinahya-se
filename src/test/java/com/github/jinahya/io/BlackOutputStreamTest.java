package com.github.jinahya.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThatCode;

class BlackOutputStreamTest {

    @Test
    void write_DoesNotThrow_() {
        assertThatCode(
                () -> BlackOutputStream.getInstance().write(ThreadLocalRandom.current().nextInt())
        ).doesNotThrowAnyException();
    }

    @Test
    void close_Idempotent_() throws IOException {
        BlackOutputStream.getInstance().close();
        assertThatCode(() -> {
            BlackOutputStream.getInstance().close();
        }).doesNotThrowAnyException();
    }
}
