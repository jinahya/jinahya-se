package com.github.jinahya.lang;

import com.google.common.primitives.Primitives;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JinahyaPrimitivesTest {

    @Nested
    class TypesTest {

        @Test
        void _True_IsPrimitive() {
            for (final Class<?> type : JinahyaPrimitives.TYPES) {
                assertThat(type.isPrimitive()).isTrue(); // https://github.com/assertj/assertj/issues/2717
            }
        }

        @Test
        void __Guava() {
            assertThat(JinahyaPrimitives.TYPES).isEqualTo(Primitives.allPrimitiveTypes());
        }
    }

    @Nested
    class IntsTest {

    }
}
