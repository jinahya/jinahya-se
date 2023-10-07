package com.github.jinahya.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class WhiteInputStreamTest {

    @Test
    void read_Between0255_() throws IOException {
        for (int i = 0; i < 1024; i++) {
            assertThat(WhiteInputStream.getInstance().read())
                    .isBetween(0, 255);
        }
    }

    @Test
    void close_Idempotent_() throws IOException {
        WhiteInputStream.getInstance().close();
        assertThatCode(() -> {
            WhiteInputStream.getInstance().close();
        }).doesNotThrowAnyException();
    }
}
