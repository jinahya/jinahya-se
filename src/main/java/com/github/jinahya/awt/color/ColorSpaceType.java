package com.github.jinahya.awt.color;

import com.github.jinahya.lang.IntFieldEnum;

import java.awt.color.ColorSpace;

public enum ColorSpaceType
        implements IntFieldEnum<ColorSpaceType> {

    TYPE_2CLR(ColorSpace.TYPE_2CLR),

    TYPE_3CLR(ColorSpace.TYPE_3CLR),

    TYPE_4CLR(ColorSpace.TYPE_4CLR),

    TYPE_5CLR(ColorSpace.TYPE_5CLR),

    TYPE_6CLR(ColorSpace.TYPE_6CLR),

    TYPE_7CLR(ColorSpace.TYPE_7CLR),

    TYPE_8CLR(ColorSpace.TYPE_8CLR),

    TYPE_9CLR(ColorSpace.TYPE_9CLR),

    TYPE_ACLR(ColorSpace.TYPE_ACLR),

    TYPE_BCLR(ColorSpace.TYPE_BCLR),

    TYPE_CCLR(ColorSpace.TYPE_CCLR),

    TYPE_CMY(ColorSpace.TYPE_CMY),

    TYPE_CMYK(ColorSpace.TYPE_CMYK),

    TYPE_DCLR(ColorSpace.TYPE_DCLR),

    TYPE_ECLR(ColorSpace.TYPE_ECLR),

    TYPE_FCLR(ColorSpace.TYPE_FCLR),

    TYPE_GRAY(ColorSpace.TYPE_GRAY),

    TYPE_HLS(ColorSpace.TYPE_HLS),

    TYPE_HSV(ColorSpace.TYPE_HSV),

    TYPE_Lab(ColorSpace.TYPE_Lab),

    TYPE_Luv(ColorSpace.TYPE_Luv),

    TYPE_RGB(ColorSpace.TYPE_RGB),

    TYPE_XYZ(ColorSpace.TYPE_XYZ),

    TYPE_YCbCr(ColorSpace.TYPE_YCbCr),

    TYPE_Yxy(ColorSpace.TYPE_Yxy);

    // -----------------------------------------------------------------------------------------------------------------
    public static ColorSpaceType valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(ColorSpaceType.class, fieldValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    ColorSpaceType(final int fieldValue) {
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
