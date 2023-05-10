package com.github.jinahya.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

final class BitMaskTestUtils {

    static final class OfLong {

        static int randomBitMaskOfLongExponent() {
            return BitMask.OfLong.requireValidExponent(
                    ThreadLocalRandom.current().nextInt(BitMask.OfLong.MIN_EXPONENT, BitMask.OfLong.MAX_EXPONENT + 1)
            );
        }

        static BitMask.OfLong randomBitMaskOfLong() {
            return BitMask.OfLong.ofExponent(randomBitMaskOfLongExponent());
        }

        static Stream<BitMask.OfLong> bitMaskOfLongStreamOfAllExponents() {
            return IntStream.rangeClosed(BitMask.OfLong.MIN_EXPONENT, BitMask.OfLong.MAX_EXPONENT)
                    .mapToObj(BitMask.OfLong::ofExponent);
        }

        static BitMask.OfLong[] bitMaskOfLongArrayOfAllExponents() {
            return bitMaskOfLongStreamOfAllExponents().toArray(BitMask.OfLong[]::new);
        }

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static int randomBitMaskExponent() {
        return BitMask.requireValidExponent(
                ThreadLocalRandom.current().nextInt(BitMask.MIN_EXPONENT, BitMask.MAX_EXPONENT + 1)
        );
    }

    static BitMask randomBitMask() {
        return BitMask.ofExponent(randomBitMaskExponent());
    }

    static Stream<BitMask> bitMaskStreamOfAllExponents() {
        return IntStream.rangeClosed(BitMask.MIN_EXPONENT, BitMask.MAX_EXPONENT)
                .mapToObj(BitMask::ofExponent);
    }

    static BitMask[] bitMaskArrayOfAllExponents() {
        return bitMaskStreamOfAllExponents().toArray(BitMask[]::new);
    }

    private BitMaskTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
