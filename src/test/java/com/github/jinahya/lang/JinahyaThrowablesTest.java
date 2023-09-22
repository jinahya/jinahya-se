package com.github.jinahya.lang;

import com.github.jinahya.nio.charset.JinahyaStandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JinahyaThrowablesTest {

    @DisplayName("printStackTrace(thrown,appendable)")
    @Nested
    class PrintStackTraceTest {

        @Test
        void __WithPrintStream() throws IOException {
            final var cause = new RuntimeException("cause");
            final var throwable = new RuntimeException("throwable", cause);
            final OutputStream stream = new ByteArrayOutputStream();
            final var result = JinahyaThrowables.printStackTrace(throwable, new PrintStream(stream)).toString();
            assertThat(result).isNotBlank();
        }

        @Test
        void __WithPrintWriter() throws IOException {
            final var cause = new RuntimeException("cause");
            final var throwable = new RuntimeException("throwable", cause);
            final Writer writer = new StringWriter();
            final var result = JinahyaThrowables.printStackTrace(throwable, new PrintWriter(writer)).toString();
            assertThat(result).isNotBlank();
        }

        @Test
        void __Appendable() throws IOException {
            final var cause = new RuntimeException("cause");
            final var throwable = new RuntimeException("throwable", cause);
            final var result = JinahyaThrowables.printStackTrace(throwable, new StringBuilder()).toString();
            assertThat(result).isNotBlank();
        }
    }

    @DisplayName("printStackTraceToStream(thrown,supplier,charset,mapper)")
    @Nested
    class PrintStackTraceToStream {

        @Test
        void __() {
            final var cause = new RuntimeException("cause");
            final var throwable = new RuntimeException("thrown", cause);
            final var result = JinahyaThrowables.printStackTraceToStream(
                    throwable,
                    ByteArrayOutputStream::new,
                    java.nio.charset.StandardCharsets.UTF_8,
                    ByteArrayOutputStream::toByteArray
            );
            assertThat(result).isNotEmpty();
        }
    }

    @DisplayName("getStackTraceBytes(thrown, charset)")
    @Nested
    class GetStackTraceBytes {

        private static List<Charset> charsetList() {
            return JinahyaStandardCharsets.getStandardCharsetList();
        }

        @MethodSource({"charsetList"})
        @ParameterizedTest
        void __(final Charset charset) {
            final var cause = new RuntimeException("cause");
            final var throwable = new RuntimeException("thrown", cause);
            final var result = JinahyaThrowables.getStackTraceBytes(
                    throwable,
                    charset
            );
            log.debug("result: {}", new String(result, charset));
            assertThat(result).isNotEmpty();
        }
    }

    @DisplayName("printStackTraceToWriter(thrown,supplier,mapepr)")
    @Nested
    class PrintStackTraceToWriterTest {

        @Test
        void __() {
            final var cause = new RuntimeException("cause");
            final var throwable = new RuntimeException("thrown", cause);
            final var result = JinahyaThrowables.printStackTraceToWriter(
                    throwable,
                    StringWriter::new,
                    StringWriter::toString
            );
            assertThat(result).isNotBlank();
        }
    }

    @DisplayName("getStackTraceString(thrown)")
    @Nested
    class GetStackTraceStringTest {

        @Test
        void __() {
            final var cause = new RuntimeException("cause");
            final var throwable = new RuntimeException("thrown", cause);
            final var result = JinahyaThrowables.getStackTraceString(throwable);
            assertThat(result).isNotBlank();
        }
    }
}
