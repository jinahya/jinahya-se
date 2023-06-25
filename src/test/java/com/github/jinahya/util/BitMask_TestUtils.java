package com.github.jinahya.util;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.util.BitMask.MAX_EXPONENT;
import static com.github.jinahya.util.BitMask.MIN_EXPONENT;
import static com.github.jinahya.util.BitMask.ofExponent;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.IntStream.rangeClosed;

final class BitMask_TestUtils {

    static int randomExponent() {
        return current().nextInt(MIN_EXPONENT, MAX_EXPONENT + 1);
    }

    static BitMask randomBitMask() {
        return ofExponent(randomExponent());
    }

    static Stream<BitMask> bitMaskStreamOfAllExponents() {
        return rangeClosed(MIN_EXPONENT, MAX_EXPONENT)
                .mapToObj(BitMask::ofExponent);
    }

    static Stream<BitMask> randomBitMaskStream() {
        return IntStream.range(0, 32).mapToObj(i -> randomBitMask());
    }

    static BitMask[] bitMaskArrayOfAllExponents() {
        return bitMaskStreamOfAllExponents().toArray(BitMask[]::new);
    }

    private BitMask_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
