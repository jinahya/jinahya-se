package com.github.jinahya.awt.color;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class JinahyaICC_ProfileUtilsTest {

    static Stream<ICC_Profile> rgbProfiles() {
        return ICC_ProfileTestUtils.getProfiles(ColorSpace.TYPE_RGB);
    }

    static Stream<ICC_Profile> cmykProfiles() {
        return ICC_ProfileTestUtils.getProfiles(ColorSpace.TYPE_CMYK);
    }

    static Stream<Arguments> rgbCmykProfiles() {
        return rgbProfiles().flatMap(r -> cmykProfiles().map(c -> arguments(r, c)));
    }

    @Test
    void getIccProfiles__() {
        final var iccProfiles = JinahyaICC_ProfileUtils.getIccProfiles();
        for (final var iccProfile : iccProfiles) {
            log.debug("iccProfile: {}", iccProfile);
            {
                final var colorSpaceType = iccProfile.getColorSpaceType();
                log.debug("\tcolorSpaceType: {}, {}", colorSpaceType, JinahyaColorSpaceType.valueOfFieldValue(colorSpaceType));
            }
            {
                final int majorVersion = iccProfile.getMajorVersion();
                log.debug("\tmajorVersion: {}", majorVersion);
            }
            {
                final int minorVersion = iccProfile.getMinorVersion();
                log.debug("\tminorVersion: {}", minorVersion);
            }
            {
                final var numComponents = iccProfile.getNumComponents();
                log.debug("\tnumComponents: {}", numComponents);
            }
            {
                final var pcsType = iccProfile.getPCSType();
                log.debug("\tPCSType: {}", pcsType);
            }
            {
                final var profileClass = iccProfile.getProfileClass();
                log.debug("\tprofileClass: {}, {}", profileClass, JinahyaICC_ProfileClass.valueOfFieldValue(profileClass));
            }
        }
    }
}
