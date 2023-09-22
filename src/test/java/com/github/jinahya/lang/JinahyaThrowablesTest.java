package com.github.jinahya.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JinahyaThrowablesTest {

    @Test
    void __WithPrintWriter() throws IOException {
        final var cause = new RuntimeException("cause");
        final var throwable = new RuntimeException("throwable", cause);
        final Writer writer = new StringWriter();
        final var result = JinahyaThrowables.printStackTrace(throwable, new PrintWriter(writer)).toString();
        assertThat(result).isNotBlank();
    }

    @Test
    void __() {
        final var cause = new RuntimeException();
        final var throwable = new RuntimeException(cause);
        final var result = JinahyaThrowables.printStackTrace(throwable);
        assertThat(result).isNotBlank();
    }
}
