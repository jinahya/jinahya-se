package com.github.jinahya.awt.color;

import lombok.extern.slf4j.Slf4j;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
final class ICC_ColorSpaceTestUtils {

    static Stream<ICC_Profile> getProfiles(final int colorSpaceType) {
        return JinahyaICC_ProfileUtils.getIccProfiles().stream()
                .filter(p -> p.getColorSpaceType() == colorSpaceType)
                ;
    }

    static Stream<ICC_Profile> rgbProfiles() {
        return getProfiles(ColorSpace.TYPE_RGB);
    }

    static Stream<ICC_Profile> cmykProfiles() {
        return getProfiles(ColorSpace.TYPE_CMYK);
    }

    static Stream<ICC_Profile> cmyProfiles() {
        return getProfiles(ColorSpace.TYPE_CMY);
    }

    static void log(final ICC_ColorSpace colorSpace) {
        Objects.requireNonNull(colorSpace, "colorSpace is null");
        log.debug("colorSpace: {}", colorSpace);
        {
            final var type = colorSpace.getType();
            log.debug("\ttype: {}, {}", type, JinahyaColorSpaceType.valueOfFieldValue(type));
        }
        final var numComponents = colorSpace.getNumComponents();
        log.debug("\tnumComponents: {}", numComponents);
        for (int i = 0; i < numComponents; i++) {
            final var name = colorSpace.getName(i);
            final var minValue = colorSpace.getMinValue(i);
            final var maxValue = colorSpace.getMaxValue(i);
            log.debug("\t\tname({}): {} [{}..{}]", i, name, minValue, maxValue);
        }
        final var profile = colorSpace.getProfile();
        ICC_ProfileTestUtils.log(profile);
        ;
    }

    private ICC_ColorSpaceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
