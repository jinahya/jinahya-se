package com.github.jinahya.util;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.util.BitMask_TestUtils.randomBitMask;
import static org.assertj.core.api.Assertions.assertThat;

class BitMask_Test {

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
