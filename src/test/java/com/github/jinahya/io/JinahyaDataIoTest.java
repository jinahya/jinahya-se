package com.github.jinahya.io;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class JinahyaDataIoTest {

    private void test(
            final CheckedFunction1<? super JinahyaDataOutput,
                    ? extends CheckedConsumer<? super JinahyaDataInput>> function)
            throws Throwable {
        Objects.requireNonNull(function, "function is null");
        try (var out = new ByteArrayOutputStream();
             var output = new JinahyaDataOutputStream(out)) {
            final var consumer = function.apply(output);
            output.flush();
            out.flush();
            try (var in = new ByteArrayInputStream(out.toByteArray());
                 var input = new JinahyaDataInputStream(in)) {
                consumer.accept(input);
            }
        }
    }

    @Test
    void shortLe__() throws Throwable {
        test(o -> {
            final var expected = (short) ThreadLocalRandom.current().nextInt();
            o.writeShortLe(expected);
            return i -> {
                final var actual = i.readShortLe();
                assertThat(actual).isEqualTo(expected);
            };
        });
    }

    @Test
    void intLe__() throws Throwable {
        test(o -> {
            final var expected = ThreadLocalRandom.current().nextInt();
            o.writeIntLe(expected);
            return i -> {
                final var actual = i.readIntLe();
                assertThat(actual).isEqualTo(expected);
            };
        });
    }

    @Test
    void longLe__() throws Throwable {
        test(o -> {
            final var expected = ThreadLocalRandom.current().nextLong();
            o.writeLongLe(expected);
            return i -> {
                final var actual = i.readLongLe();
                assertThat(actual).isEqualTo(expected);
            };
        });
    }
}
