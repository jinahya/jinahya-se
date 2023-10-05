package com.github.jinahya.awt.color;

import com.github.jinahya.lang.IntFieldEnum;

import java.awt.color.ColorSpace;

public enum ColorSpaceCs
        implements IntFieldEnum<ColorSpaceCs> {

    CS_CIEXYZ(ColorSpace.CS_CIEXYZ),

    CS_GRAY(ColorSpace.CS_GRAY),

    CS_LINEAR_RGB(ColorSpace.CS_LINEAR_RGB),

    CS_PYCC(ColorSpace.CS_PYCC),

    CS_sRGB(ColorSpace.CS_sRGB),
    ;

    // -----------------------------------------------------------------------------------------------------------------
    public static ColorSpaceCs valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(ColorSpaceCs.class, fieldValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    ColorSpaceCs(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public int getFieldValue() {
        return fieldValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final int fieldValue;
}
