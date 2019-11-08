package com.github.jinahya.io;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class JinahyaDataInputStreamTest {

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<Arguments> sourceJinahyaDataInputStream() {
        return Stream.of(Arguments.of(new JinahyaDataInputStream(new DataInputStream(new WhiteInputStream()))));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataInputStream"})
    @ParameterizedTest
    public void testReadShortLe(final JinahyaDataInputStream input) throws IOException {
        final short v = input.readShortLe();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataInputStream"})
    @ParameterizedTest
    public void testReadIntLe(final JinahyaDataInputStream input) throws IOException {
        final int v = input.readIntLe();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceJinahyaDataInputStream"})
    @ParameterizedTest
    public void testReadLongLe(final JinahyaDataInputStream input) throws IOException {
        final long v = input.readLongLe();
    }
}
