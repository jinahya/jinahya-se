package com.github.jinahya.io;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Disabled
class DataIoTest {

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<Arguments> sourceJinahyaDataIo() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        return Stream.of(arguments(
                new JinahyaDataOutputStream(new DataOutputStream(baos)),
                (Supplier<JinahyaDataInput>) () ->
                        new JinahyaDataInputStream(new DataInputStream(new ByteArrayInputStream(baos.toByteArray())))));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataIo"})
    @ParameterizedTest
    void testShortLe(final JinahyaDataOutput output, final Supplier<JinahyaDataInput> supplier) throws IOException {
        final int expected = current().nextInt() >>> Short.SIZE;
        output.writeShortLe(expected);
        final short actual = supplier.get().readShortLe();
        assertEquals(expected, actual);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataIo"})
    @ParameterizedTest
    void testIntLe(final JinahyaDataOutput output, final Supplier<JinahyaDataInput> supplier) throws IOException {
        final int expected = current().nextInt();
        output.writeIntLe(expected);
        final int actual = supplier.get().readIntLe();
        assertEquals(expected, actual);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataIo"})
    @ParameterizedTest
    void testLongLe(final JinahyaDataOutput output, final Supplier<JinahyaDataInput> supplier) throws IOException {
        final long expected = current().nextLong();
        output.writeLongLe(expected);
        final long actual = supplier.get().readLongLe();
        assertEquals(expected, actual);
    }
}
