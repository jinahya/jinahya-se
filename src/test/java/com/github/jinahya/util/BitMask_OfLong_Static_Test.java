package com.github.jinahya.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BitMask_OfLong_Static_Test {

    @Test
    void ofExponent__() {
        for (int e = BitMask.OfLong.MIN_EXPONENT; e <= BitMask.OfLong.MAX_EXPONENT; e++) {
            final var instance = BitMask.OfLong.ofExponent(e);
            assertThat(instance).isNotNull();
        }
    }
}
