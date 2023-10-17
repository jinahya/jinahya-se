package com.github.jinahya.awt.color;

import com.github.jinahya.lang.IntFieldEnum;

import java.awt.color.ColorSpace;

/**
 * Constants for fields whose names start with {@code CS_} defined in {@link ColorSpace} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum JinahyaColorSpaceCs
        implements IntFieldEnum<JinahyaColorSpaceCs> {

    /**
     * Constants for {@link ColorSpace#CS_CIEXYZ}({@value ColorSpace#CS_CIEXYZ}).
     */
    CS_CIEXYZ(ColorSpace.CS_CIEXYZ),

    CS_GRAY(ColorSpace.CS_GRAY),

    CS_LINEAR_RGB(ColorSpace.CS_LINEAR_RGB),

    CS_PYCC(ColorSpace.CS_PYCC),

    CS_sRGB(ColorSpace.CS_sRGB),
    ;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the value whose {@link #getFieldValue() fieldValue} equals to specified value.
     *
     * @param fieldValue the value of {@link #getFieldValue() fieldValue} to match.
     * @return the value whose {@link #getFieldValue()} equals to {@code fieldValue}.
     * @throws IllegalArgumentException when no value matched.
     */
    public static JinahyaColorSpaceCs valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(JinahyaColorSpaceCs.class, fieldValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    JinahyaColorSpaceCs(final int fieldValue) {
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
