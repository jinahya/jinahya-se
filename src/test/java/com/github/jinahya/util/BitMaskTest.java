package com.github.jinahya.util;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.util.BitMaskTestUtils.OfLong.randomBitMaskOfLong;
import static com.github.jinahya.util.BitMaskTestUtils.randomBitMask;
import static org.assertj.core.api.Assertions.assertThat;

class BitMaskTest {

    @Nested
    class OfLongTest {

        @Test
        void ofExponent__() {
            for (int exponent = BitMask.OfLong.MIN_EXPONENT; exponent <= BitMask.OfLong.MAX_EXPONENT; exponent++) {
                final var instance = BitMask.OfLong.ofExponent(exponent);
                assertThat(instance).isNotNull();
            }
        }

        @Test
        void equals__() {
            EqualsVerifier.forClass(BitMask.OfLong.class).verify();
        }

        @Test
        void toString__() {
            final var mask = randomBitMaskOfLong();
            assertThat(mask.toString()).isNotBlank();
        }

        @Test
        void putOnTo__() {
            final var mask = randomBitMaskOfLong();
            final var face = mask.putOnTo(BitFaceTestUtils.OfLong.randomBitFaceOfLong());
            assertThat(face).isNotNull();
        }

        @Test
        void takeOffFrom__() {
            final var mask = randomBitMaskOfLong();
            final var face = mask.takeOffFrom(BitFaceTestUtils.OfLong.randomBitFaceOfLong());
            assertThat(face).isNotNull();
        }
    }

    @Test
    void ofExponent__() {
        for (int exponent = BitMask.MIN_EXPONENT; exponent <= BitMask.MAX_EXPONENT; exponent++) {
            final var instance = BitMask.ofExponent(exponent);
            assertThat(instance).isNotNull();
        }
    }

    @Test
    void equals__() {
        EqualsVerifier.forClass(BitMask.class).verify();
    }

    @Test
    void toString__() {
        final var mask = randomBitMask();
        assertThat(mask.toString()).isNotBlank();
    }

    @Test
    void putOnTo__() {
        final var mask = randomBitMask();
        final var face = mask.putOnTo(BitFaceTestUtils.randomBitFace());
    }

    @Test
    void takeOffFrom__() {
        final var mask = randomBitMask();
        final var face = mask.takeOffFrom(BitFaceTestUtils.randomBitFace());
    }
}
