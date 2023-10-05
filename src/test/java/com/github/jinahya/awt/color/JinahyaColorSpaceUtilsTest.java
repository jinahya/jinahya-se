package com.github.jinahya.awt.color;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class JinahyaColorSpaceUtilsTest {

    @Test
    void getIccProfiles__() {
        final var iccProfiles = JinahyaColorSpaceUtils.getIccProfiles();
        for (final var iccProfile : iccProfiles) {
            log.debug("iccProfile: {}", iccProfile);
            {
                final var colorSpaceType = iccProfile.getColorSpaceType();
                log.debug("\tcolorSpaceType: {}, {}", colorSpaceType, ColorSpaceType.valueOfFieldValue(colorSpaceType));
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
                log.debug("\tprofileClass: {}, {}", profileClass, ICC_ProfileClass.valueOfFieldValue(profileClass));
            }
        }
    }
}
