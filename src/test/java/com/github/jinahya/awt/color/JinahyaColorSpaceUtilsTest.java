package com.github.jinahya.awt.color;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static com.github.jinahya.awt.color.ICC_ProfileTestUtils.cmykProfiles;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class JinahyaColorSpaceUtilsTest {

    private static Stream<ICC_Profile> rgbProfiles() {
        return ICC_ProfileTestUtils.getProfiles(ColorSpace.TYPE_RGB)
                .peek(p -> {
                    log.debug("{} {}", p.getColorSpaceType(), ColorSpaceType.valueOfFieldValue(p.getColorSpaceType()));
                });
    }

    static Stream<Arguments> rgbCmykProfiles() {
        return rgbProfiles().flatMap(r -> cmykProfiles().map(c -> arguments(r, c)));
    }

    @Test
    void __() {
        rgbProfiles().forEach(rgbp -> {
            log.debug("rgbp: {}", rgbp);
        });
        cmykProfiles().forEach(cmykp -> {
            log.debug("cmykp: {}", cmykp);
        });
        ICC_ProfileTestUtils.cmyProfiles().forEach(cmyp -> {
            log.debug("cmyp: {}", cmyp);
        });
    }

    @MethodSource({"rgbProfiles"})
    @ParameterizedTest
    void rgbToCiexyz__(final ICC_Profile rgbProfile) {
        final var colorSpace = new ICC_ColorSpace(rgbProfile);
        final var color = new Color(
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256),
                ThreadLocalRandom.current().nextInt(256)
        );
        final float[] colorComponents = color.getColorComponents(null);
        log.debug("RGB color components: {}", Arrays.toString(colorComponents));
        final float[] ciezyz = JinahyaColorSpaceUtils.rgbToCiexyz(colorSpace, colorComponents);
    }
}
