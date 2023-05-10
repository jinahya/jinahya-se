package com.github.jinahya.util;

import java.util.concurrent.ThreadLocalRandom;

final class BitFaceTestUtils {

    static final class OfLong {

        static long randomBitFaceOfLongValue() {
            return ThreadLocalRandom.current().nextLong() >>> 1;
        }

        static BitFace.OfLong randomBitFaceOfLong() {
            return BitFace.OfLong.of(randomBitFaceOfLongValue());
        }

        private OfLong() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static int randomBitFaceValue() {
        return ThreadLocalRandom.current().nextInt() >>> 1;
    }

    static BitFace randomBitFace() {
        return BitFace.of(randomBitFaceValue());
    }

    private BitFaceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
