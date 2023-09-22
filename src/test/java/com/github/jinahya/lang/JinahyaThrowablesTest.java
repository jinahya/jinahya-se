package com.github.jinahya.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

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
    void printStackTraceAsBytes__() {
        final var cause = new RuntimeException("cause");
        final var throwable = new RuntimeException("thrown", cause);
        final var result = JinahyaThrowables.printStackTraceAsBytes(throwable, StandardCharsets.UTF_8);
        log.debug("result: {}", new String(result));
        assertThat(result).isNotEmpty();
    }

    @Test
    void printStackTraceAsString__() {
        final var cause = new RuntimeException("cause");
        final var throwable = new RuntimeException("thrown", cause);
        final var result = JinahyaThrowables.printStackTraceAsString(throwable);
        log.debug("result: {}", result);
        assertThat(result).isNotBlank();
    }
}
