package com.github.jinahya.awt.color;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class JinahyaColorSpaceUtilsTest {

    private static Stream<? extends ColorSpace> rgbColorSpaceXyzStream() {
        return JinahyaICC_ProfileUtilsTest.rgbProfiles()
                .filter(p -> p.getPCSType() == ColorSpace.TYPE_XYZ)
                .map(ICC_ColorSpace::new);
    }

    private static Stream<? extends ColorSpace> rgbColorSpaceStream() {
        return JinahyaICC_ColorSpaceUtils.getIccColorSpaces().stream()
                .filter(cp -> cp.getType() == ColorSpace.TYPE_RGB);
    }

    private static Stream<? extends ColorSpace> cmykColorSpaceStream() {
        return JinahyaICC_ColorSpaceUtils.getIccColorSpaces()
                .stream()
                .filter(cp -> cp.getType() == java.awt.color.ColorSpace.TYPE_CMYK);
    }

    static Stream<Arguments> rgbCmykColorSpacesArgumentsStream() {
        return rgbColorSpaceStream().flatMap(rgbcs -> cmykColorSpaceStream().map(cmykcs -> arguments(rgbcs, cmykcs)));
    }

    @Test
    void getIccProfiles__() {
        final var colorSpaces = JinahyaICC_ColorSpaceUtils.getIccColorSpaces();
        for (final var colorSpace : colorSpaces) {
            ICC_ColorSpaceTestUtils.log(colorSpace);
        }
    }

    @DisplayName("rgbToCiexyz")
    @MethodSource({"rgbColorSpaceXyzStream"})
    @ParameterizedTest
    void rgbToCiexyz__(final ColorSpace colorSpace) {
        final var color = new Color(
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256)
        );
        {
            final float[] colorComponents = color.getColorComponents(null);
            final float[] ciexyz = JinahyaColorSpaceUtils.rgbToCiexyz(colorSpace, colorComponents);
            assertThat(ciexyz).isNotNull().hasSize(3);
        }
        {
            final float[] components = color.getComponents(null);
            final float[] ciexyz = JinahyaColorSpaceUtils.rgbToCiexyz(colorSpace, components);
            assertThat(ciexyz).isNotNull().hasSize(3);
        }
    }

    @DisplayName("rgbToCmyk(cmykColorSpace, rgbColorComponents, rgbColorSpace)")
    @MethodSource({"rgbCmykColorSpacesArgumentsStream"})
    @ParameterizedTest
    void rgbToCmyk__(final ColorSpace rgbColorSpace, final ColorSpace cmykColorSpace) {
        if (rgbColorSpace.getType() != ColorSpace.TYPE_XYZ) {
            return;
        }
        final var color = new Color(
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256)
        );
        final float[] rgbColorComponents = color.getColorComponents(null);
        {
            final float[] ciexyz = JinahyaColorSpaceUtils.rgbToCmyk(cmykColorSpace, rgbColorComponents, rgbColorSpace);
            assertThat(ciexyz).isNotNull().hasSize(3);
        }
        {
            final float[] ciexyz = JinahyaColorSpaceUtils.rgbToCmyk(cmykColorSpace, rgbColorComponents, null);
            assertThat(ciexyz).isNotNull().hasSize(3);
        }
    }

    @DisplayName("rgbToCmyk(cmykColorSpace, rgbColorComponents, null)")
    @MethodSource({"cmykColorSpaceStream"})
    @ParameterizedTest
    void rgbToCmyk__NullRgbColorSpace(final ColorSpace cmykColorSpace) {
        final var color = new Color(
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256)
        );
        final float[] rgbColorComponents = color.getColorComponents(null);
        final float[] ciexyz = JinahyaColorSpaceUtils.rgbToCmyk(cmykColorSpace, rgbColorComponents, null);
        assertThat(ciexyz).isNotNull().hasSize(4);
    }
}
