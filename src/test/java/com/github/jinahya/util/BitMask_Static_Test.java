package com.github.jinahya.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BitMask_Static_Test {

    @Nested
    class OfLongTest {

        @Test
        void ofExponent__() {
            for (int e = BitMask.OfLong.MIN_EXPONENT; e <= BitMask.OfLong.MAX_EXPONENT; e++) {
                final var instance = BitMask.OfLong.ofExponent(e);
                assertThat(instance).isNotNull();
            }
        }
    }

    @Test
    void ofExponent__() {
        for (int e = BitMask.MIN_EXPONENT; e <= BitMask.MAX_EXPONENT; e++) {
            final var instance = BitMask.ofExponent(e);
            assertThat(instance).isNotNull();
        }
    }
}
