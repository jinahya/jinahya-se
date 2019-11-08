package com.github.jinahya.io;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

import static java.util.concurrent.ThreadLocalRandom.current;

public class JinahyaDataOutputStreamTest {

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<Arguments> sourceJinahyaDataOutputStream() {
        return Stream.of(Arguments.of(new JinahyaDataOutputStream(new DataOutputStream(new BlackOutputStream()))));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataOutputStream"})
    @ParameterizedTest
    public void testWriteShortLe(final JinahyaDataOutputStream output) throws IOException {
        output.writeShortLe(current().nextInt());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataOutputStream"})
    @ParameterizedTest
    public void testReadIntLe(final JinahyaDataOutputStream output) throws IOException {
        output.writeIntLe(current().nextInt());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataOutputStream"})
    @ParameterizedTest
    public void testWriteLongLe(final JinahyaDataOutputStream output) throws IOException {
        output.writeLongLe(current().nextLong());
    }
}
