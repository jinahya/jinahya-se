package com.github.jinahya.util;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.util.BitMask.OfLong.MAX_EXPONENT;
import static com.github.jinahya.util.BitMask.OfLong.MIN_EXPONENT;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.IntStream.rangeClosed;

final class BitMask_OfLong_TestUtils {

    static int randomExponent() {
        return current().nextInt(MIN_EXPONENT, MAX_EXPONENT + 1);
    }

    static BitMask.OfLong randomBitMask() {
        return BitMask.OfLong.ofExponent(randomExponent());
    }

    static Stream<BitMask.OfLong> randomBitMaskStream() {
        return IntStream.range(0, 64).mapToObj(i -> randomBitMask());
    }

    static Stream<BitMask.OfLong> bitMaskStreamOfAllExponents() {
        return rangeClosed(MIN_EXPONENT, MAX_EXPONENT)
                .mapToObj(BitMask.OfLong::ofExponent);
    }

    private BitMask_OfLong_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
