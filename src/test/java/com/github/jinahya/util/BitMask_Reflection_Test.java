package com.github.jinahya.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BitMask_Reflection_Test {

    @Test
    void __() throws Exception {
        final var constructor = BitMask.class.getDeclaredConstructor(int.class);
        if (!constructor.canAccess(null)) {
            constructor.setAccessible(true);
        }
        assertThatThrownBy(() -> constructor.newInstance(1))
                .isNotNull();
    }
}
