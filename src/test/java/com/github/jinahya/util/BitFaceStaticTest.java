package com.github.jinahya.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.github.jinahya.util.BitMaskTestUtils.OfLong.bitMaskOfLongArrayOfAllExponents;
import static com.github.jinahya.util.BitMaskTestUtils.OfLong.bitMaskOfLongStreamOfAllExponents;
import static com.github.jinahya.util.BitMaskTestUtils.bitMaskArrayOfAllExponents;
import static com.github.jinahya.util.BitMaskTestUtils.bitMaskStreamOfAllExponents;
import static org.assertj.core.api.Assertions.assertThat;

class BitFaceStaticTest {

    @Nested
    class OfLong {

        @DisplayName("of(Iterable)")
        @Test
        void of__Iterable() {
            assertThat(BitFace.OfLong.of(bitMaskOfLongStreamOfAllExponents().toList()))
                    .isEqualTo(BitFace.OfLong.ofAll());
        }

        @DisplayName("of(Iterable)")
        @Test
        void of__Array() {
            assertThat(BitFace.OfLong.of(Arrays.asList(bitMaskOfLongArrayOfAllExponents())))
                    .isEqualTo(BitFace.OfLong.ofAll());
        }
    }

    @DisplayName("of(Iterable)")
    @Test
    void of__Iterable() {
        assertThat(BitFace.of(bitMaskStreamOfAllExponents().toList()))
                .isEqualTo(BitFace.ofAll());
    }

    @DisplayName("of([])")
    @Test
    void of__Array() {
        assertThat(BitFace.of(Arrays.asList(bitMaskArrayOfAllExponents())))
                .isEqualTo(BitFace.ofAll());
    }
}
