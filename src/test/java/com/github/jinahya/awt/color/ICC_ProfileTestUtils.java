package com.github.jinahya.awt.color;

import lombok.extern.slf4j.Slf4j;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;
import java.util.Objects;
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

    static void log(final ICC_Profile profile) {
        Objects.requireNonNull(profile, "profile is null");
        log.debug("profile: {}", profile);
        {
            final var colorSpaceType = profile.getColorSpaceType();
            log.debug("\tcolorSpaceType: {}, {}", colorSpaceType, ColorSpaceType.valueOfFieldValue(colorSpaceType));
        }
        {
            final int majorVersion = profile.getMajorVersion();
            log.debug("\tmajorVersion: {}", majorVersion);
        }
        {
            final int minorVersion = profile.getMinorVersion();
            log.debug("\tminorVersion: {}", minorVersion);
        }
        {
            final var numComponents = profile.getNumComponents();
            log.debug("\tnumComponents: {}", numComponents);
        }
        {
            final var pcsType = profile.getPCSType();
            log.debug("\tPCSType: {}", pcsType);
        }
        {
            final var profileClass = profile.getProfileClass();
            log.debug("\tprofileClass: {}, {}", profileClass, ICC_ProfileClass.valueOfFieldValue(profileClass));
        }
    }

    private ICC_ProfileTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
