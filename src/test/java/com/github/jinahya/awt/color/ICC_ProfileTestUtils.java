package com.github.jinahya.awt.color;

import lombok.extern.slf4j.Slf4j;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;
import java.util.stream.Stream;

@Slf4j
final class ICC_ProfileTestUtils {

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

    private ICC_ProfileTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
