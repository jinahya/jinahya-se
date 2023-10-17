package com.github.jinahya.awt.color;

import java.awt.color.ICC_ColorSpace;
import java.util.List;

public final class JinahyaICC_ColorSpaceUtils {

    /**
     * Returns an unmodifiable list of {@link ICC_ColorSpace}s available on the system.
     *
     * @return an unmodifiable list of {@link ICC_ColorSpace}s.
     */
    public static List<ICC_ColorSpace> getIccColorSpaces() {
        return JinahyaICC_ProfileUtils.getIccProfiles()
                .stream()
                .map(ICC_ColorSpace::new)
                .toList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaICC_ColorSpaceUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
