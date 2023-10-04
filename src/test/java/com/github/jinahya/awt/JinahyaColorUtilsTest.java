package com.github.jinahya.awt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JinahyaColorUtilsTest {

    static float randomComponent() {
        return JinahyaColorUtils.toComponent(randomColor());
    }

    static int randomColor() {
        return ThreadLocalRandom.current().nextInt(JinahyaColorUtils.MIN_COLOR, JinahyaColorUtils.MAX_COLOR + 1);
    }

    static int[] randomColors() {
        final int[] colors = new int[4];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = randomColor();
        }
        return colors;
    }

    static float[] randomComponents() {
        return JinahyaColorUtils.toComponents(randomColors());
    }

    @DisplayName("toCssRgbHexadecimalNotation3")
    @Nested
    class ToCssRgbHexadecimalNotation3Test {

        @DisplayName("(components)")
        @Test
        void __() {
            final var components = randomComponents();
            final var result = JinahyaColorUtils.toCssRgbHexadecimalNotation3(components);
            assertThat(result)
                    .hasSize(3)
                    .matches(JinahyaColorUtils.PATTERN_CSS_HEXADECIMAL_NOTATION3);
        }
    }

    @DisplayName("toCssRgbHexadecimalNotation4")
    @Nested
    class ToCssRgbHexadecimalNotation4Test {

        @DisplayName("(components)")
        @Test
        void __() {
            final var components = randomComponents();
            final var result = JinahyaColorUtils.toCssRgbHexadecimalNotation4(components);
            assertThat(result)
                    .hasSize(4)
                    .matches(JinahyaColorUtils.PATTERN_CSS_HEXADECIMAL_NOTATION4);
        }
    }

    @DisplayName("toCssRgbHexadecimalNotation6")
    @Nested
    class ToCssRgbHexadecimalNotation6Test {

        @DisplayName("(components)")
        @Test
        void __() {
            final var components = randomComponents();
            final var result = JinahyaColorUtils.toCssRgbHexadecimalNotation6(components);
            assertThat(result)
                    .hasSize(6)
                    .matches(JinahyaColorUtils.PATTERN_CSS_HEXADECIMAL_NOTATION6);
        }
    }

    @DisplayName("toCssRgbHexadecimalNotation8")
    @Nested
    class ToCssRgbHexadecimalNotation8Test {

        @DisplayName("(components)")
        @Test
        void __() {
            final var components = randomComponents();
            final var result = JinahyaColorUtils.toCssRgbHexadecimalNotation8(components);
            assertThat(result)
                    .hasSize(8)
                    .matches(JinahyaColorUtils.PATTERN_CSS_HEXADECIMAL_NOTATION8);
        }
    }
}
