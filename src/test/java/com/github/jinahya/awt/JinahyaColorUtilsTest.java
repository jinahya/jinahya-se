package com.github.jinahya.awt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.awt.JinahyaColorUtils.parseCssRgbHexadecimalNotation;
import static com.github.jinahya.awt.JinahyaColorUtils.toInt;
import static com.github.jinahya.awt.JinahyaColorUtils.toCssRgbHexadecimalNotation3;
import static com.github.jinahya.awt.JinahyaColorUtils.toCssRgbHexadecimalNotation4;
import static com.github.jinahya.awt.JinahyaColorUtils.toCssRgbHexadecimalNotation6;
import static com.github.jinahya.awt.JinahyaColorUtils.toCssRgbHexadecimalNotation8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class JinahyaColorUtilsTest {

    static int randomColor() {
        return ThreadLocalRandom.current().nextInt(JinahyaColorUtils.MIN_COLOR, JinahyaColorUtils.MAX_COLOR + 1);
    }

    static float randomComponent() {
        return JinahyaColorUtils.toFloat(randomColor());
    }

    static int[] randomColors() {
        final var colors = new int[4];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = randomColor();
        }
        return colors;
    }

    static float[] randomComponents() {
        final var components = new float[4];
        for (int i = 0; i < components.length; i++) {
            components[i] = randomComponent();
        }
        return components;
    }

    private static Stream<Arguments> randomCssRgbHexadecimalNotation3() {
        return IntStream.range(0, 128).mapToObj(i -> {
            final var components = randomComponents();
            return arguments(components, toCssRgbHexadecimalNotation3(components));
        });
    }

    private static Stream<Arguments> randomCssRgbHexadecimalNotation4() {
        return IntStream.range(0, 128).mapToObj(i -> {
            final var components = randomComponents();
            return arguments(components, toCssRgbHexadecimalNotation4(components));
        });
    }

    private static Stream<Arguments> randomCssRgbHexadecimalNotation6() {
        return IntStream.range(0, 128).mapToObj(i -> {
            final var components = randomComponents();
            return arguments(components, toCssRgbHexadecimalNotation6(components));
        });
    }

    private static Stream<Arguments> randomCssRgbHexadecimalNotation8() {
        return IntStream.range(0, 128).mapToObj(i -> {
            final var components = randomComponents();
            return arguments(components, toCssRgbHexadecimalNotation8(components));
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    @RepeatedTest(128)
    void toInt__() {
        final var component = ThreadLocalRandom.current().nextFloat();
        final var color = JinahyaColorUtils.toInt(component);
        assertThat(color).isBetween(JinahyaColorUtils.MIN_COLOR, JinahyaColorUtils.MAX_COLOR);
    }

    @RepeatedTest(128)
    void toFloat__() {
        final var color = ThreadLocalRandom.current().nextInt();
        final var component = JinahyaColorUtils.toFloat(color);
        assertThat(component).isBetween(JinahyaColorUtils.MIN_COMPONENT, JinahyaColorUtils.MAX_COMPONENT);
    }

    @DisplayName("toCssRgbHexadecimalNotation3")
    @Nested
    class ToCssRgbHexadecimalNotation3Test {

        @DisplayName("(components)")
        @Test
        void __() {
            final var components = randomComponents();
            final var result = toCssRgbHexadecimalNotation3(components);
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
            final var result = toCssRgbHexadecimalNotation4(components);
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
            final var result = toCssRgbHexadecimalNotation6(components);
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
            final var result = toCssRgbHexadecimalNotation8(components);
            assertThat(result)
                    .hasSize(8)
                    .matches(JinahyaColorUtils.PATTERN_CSS_HEXADECIMAL_NOTATION8);
        }
    }

    @Nested
    class ParseCssTEst {

        private static Stream<Arguments> randomCssRgbHexadecimalNotation3_() {
            return randomCssRgbHexadecimalNotation3();
        }

        private static Stream<Arguments> randomCssRgbHexadecimalNotation4_() {
            return randomCssRgbHexadecimalNotation4();
        }

        private static Stream<Arguments> randomCssRgbHexadecimalNotation6_() {
            return randomCssRgbHexadecimalNotation6();
        }

        private static Stream<Arguments> randomCssRgbHexadecimalNotation8_() {
            return randomCssRgbHexadecimalNotation8();
        }

        @MethodSource({"randomCssRgbHexadecimalNotation3_"})
        @ParameterizedTest
        void __3(final float[] components, final String cssRgbHexadecimalNotation3) {
            parseCssRgbHexadecimalNotation(
                    cssRgbHexadecimalNotation3,
                    r -> g -> b -> a -> {
                        assertThat(r).isEqualTo(toInt(components[0]) >> 4);
                        assertThat(g).isEqualTo(toInt(components[1]) >> 4);
                        assertThat(b).isEqualTo(toInt(components[2]) >> 4);
                        assertThat(a).isZero();
                        return null;
                    }
            );
        }

        @MethodSource({"randomCssRgbHexadecimalNotation4_"})
        @ParameterizedTest
        void __4(final float[] components, final String cssRgbHexadecimalNotation4) {
            parseCssRgbHexadecimalNotation(
                    cssRgbHexadecimalNotation4,
                    r -> g -> b -> a -> {
                        assertThat(r).isEqualTo(toInt(components[0]) >> 4);
                        assertThat(g).isEqualTo(toInt(components[1]) >> 4);
                        assertThat(b).isEqualTo(toInt(components[2]) >> 4);
                        assertThat(a).isEqualTo(toInt(components[3]) >> 4);
                        return null;
                    }
            );
        }

        @MethodSource({"randomCssRgbHexadecimalNotation6_"})
        @ParameterizedTest
        void __6(final float[] components, final String cssRgbHexadecimalNotation6) {
            parseCssRgbHexadecimalNotation(
                    cssRgbHexadecimalNotation6,
                    r -> g -> b -> a -> {
                        assertThat(r).isEqualTo(toInt(components[0]));
                        assertThat(g).isEqualTo(toInt(components[1]));
                        assertThat(b).isEqualTo(toInt(components[2]));
                        assertThat(a).isZero();
                        return null;
                    }
            );
        }

        @MethodSource({"randomCssRgbHexadecimalNotation8_"})
        @ParameterizedTest
        void __8(final float[] components, final String cssRgbHexadecimalNotation8) {
            parseCssRgbHexadecimalNotation(
                    cssRgbHexadecimalNotation8,
                    r -> g -> b -> a -> {
                        assertThat(r).isEqualTo(toInt(components[0]));
                        assertThat(g).isEqualTo(toInt(components[1]));
                        assertThat(b).isEqualTo(toInt(components[2]));
                        assertThat(a).isEqualTo(toInt(components[3]));
                        return null;
                    }
            );
        }
    }
}
