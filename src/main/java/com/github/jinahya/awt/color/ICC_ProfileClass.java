package com.github.jinahya.awt.color;

import com.github.jinahya.lang.IntFieldEnum;

import java.awt.color.ICC_Profile;

public enum ICC_ProfileClass
        implements IntFieldEnum<ICC_ProfileClass> {

    /**
     * Constants for {@link ICC_Profile#CLASS_ABSTRACT}.
     */
    CLASS_ABSTRACT(ICC_Profile.CLASS_ABSTRACT),

    CLASS_COLORSPACECONVERSION(ICC_Profile.CLASS_COLORSPACECONVERSION),

    CLASS_DEVICELINK(ICC_Profile.CLASS_DEVICELINK),

    CLASS_DISPLAY(ICC_Profile.CLASS_DISPLAY),

    CLASS_INPUT(ICC_Profile.CLASS_INPUT),

    /**
     * Constant for {@link ICC_Profile#CLASS_NAMEDCOLOR}.
     */
    CLASS_NAMEDCOLOR(ICC_Profile.CLASS_NAMEDCOLOR),

    CLASS_OUTPUT(ICC_Profile.CLASS_OUTPUT);

    // -----------------------------------------------------------------------------------------------------------------
    public static ICC_ProfileClass valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(ICC_ProfileClass.class, fieldValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    ICC_ProfileClass(final int fieldValue) {
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
