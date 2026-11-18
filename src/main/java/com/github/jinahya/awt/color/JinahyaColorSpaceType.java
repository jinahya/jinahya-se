package com.github.jinahya.awt.color;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.lang.IntFieldEnum;

import java.awt.color.ColorSpace;

/**
 * Constants for fields names start with {@code TYPE_} defined in {@link ColorSpace} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum JinahyaColorSpaceType
        implements IntFieldEnum<JinahyaColorSpaceType> {

    /**
     * Constant for {@link ColorSpace#TYPE_2CLR}({@value ColorSpace#TYPE_2CLR}).
     */
    TYPE_2CLR(ColorSpace.TYPE_2CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_3CLR}({@value ColorSpace#TYPE_3CLR}).
     */
    TYPE_3CLR(ColorSpace.TYPE_3CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_4CLR}({@value ColorSpace#TYPE_4CLR}).
     */
    TYPE_4CLR(ColorSpace.TYPE_4CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_5CLR}({@value ColorSpace#TYPE_5CLR}).
     */
    TYPE_5CLR(ColorSpace.TYPE_5CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_6CLR}({@value ColorSpace#TYPE_6CLR}).
     */
    TYPE_6CLR(ColorSpace.TYPE_6CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_7CLR}({@value ColorSpace#TYPE_7CLR}).
     */
    TYPE_7CLR(ColorSpace.TYPE_7CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_8CLR}({@value ColorSpace#TYPE_8CLR}).
     */
    TYPE_8CLR(ColorSpace.TYPE_8CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_9CLR}({@value ColorSpace#TYPE_9CLR}).
     */
    TYPE_9CLR(ColorSpace.TYPE_9CLR),

    /**
     * Constant for {@link ColorSpace#TYPE_ACLR}({@value ColorSpace#TYPE_ACLR}).
     */
    TYPE_ACLR(ColorSpace.TYPE_ACLR),

    /**
     * Constant for {@link ColorSpace#TYPE_BCLR}({@value ColorSpace#TYPE_BCLR}).
     */
    TYPE_BCLR(ColorSpace.TYPE_BCLR),

    /**
     * Constant for {@link ColorSpace#TYPE_CCLR}({@value ColorSpace#TYPE_CCLR}).
     */
    TYPE_CCLR(ColorSpace.TYPE_CCLR),

    /**
     * Constant for {@link ColorSpace#TYPE_CMY}({@value ColorSpace#TYPE_CMY}).
     */
    TYPE_CMY(ColorSpace.TYPE_CMY),

    /**
     * Constant for {@link ColorSpace#TYPE_CMYK}({@value ColorSpace#TYPE_CMYK}).
     */
    TYPE_CMYK(ColorSpace.TYPE_CMYK),

    /**
     * Constant for {@link ColorSpace#TYPE_DCLR}({@value ColorSpace#TYPE_DCLR}).
     */
    TYPE_DCLR(ColorSpace.TYPE_DCLR),

    /**
     * Constant for {@link ColorSpace#TYPE_ECLR}({@value ColorSpace#TYPE_ECLR}).
     */
    TYPE_ECLR(ColorSpace.TYPE_ECLR),

    /**
     * Constant for {@link ColorSpace#TYPE_FCLR}({@value ColorSpace#TYPE_FCLR}).
     */
    TYPE_FCLR(ColorSpace.TYPE_FCLR),

    /**
     * Constant for {@link ColorSpace#TYPE_GRAY}({@value ColorSpace#TYPE_GRAY}).
     */
    TYPE_GRAY(ColorSpace.TYPE_GRAY),

    /**
     * Constant for {@link ColorSpace#TYPE_HLS}({@value ColorSpace#TYPE_HLS}).
     */
    TYPE_HLS(ColorSpace.TYPE_HLS),

    /**
     * Constant for {@link ColorSpace#TYPE_HSV}({@value ColorSpace#TYPE_HSV}).
     */
    TYPE_HSV(ColorSpace.TYPE_HSV),

    /**
     * Constant for {@link ColorSpace#TYPE_Lab}({@value ColorSpace#TYPE_Lab}).
     */
    TYPE_Lab(ColorSpace.TYPE_Lab),

    /**
     * Constant for {@link ColorSpace#TYPE_Luv}({@value ColorSpace#TYPE_Luv}).
     */
    TYPE_Luv(ColorSpace.TYPE_Luv),

    /**
     * Constant for {@link ColorSpace#TYPE_RGB}({@value ColorSpace#TYPE_RGB}).
     */
    TYPE_RGB(ColorSpace.TYPE_RGB),

    /**
     * Constant for {@link ColorSpace#TYPE_XYZ}({@value ColorSpace#TYPE_XYZ}).
     */
    TYPE_XYZ(ColorSpace.TYPE_XYZ),

    /**
     * Constant for {@link ColorSpace#TYPE_YCbCr}({@value ColorSpace#TYPE_YCbCr}).
     */
    TYPE_YCbCr(ColorSpace.TYPE_YCbCr),

    /**
     * Constant for {@link ColorSpace#TYPE_Yxy}({@value ColorSpace#TYPE_Yxy}).
     */
    TYPE_Yxy(ColorSpace.TYPE_Yxy);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the value whose {@link #getFieldValue() fieldValue} equals to specified value.
     *
     * @param fieldValue the value of {@link #getFieldValue() fieldValue} to match.
     * @return the value whose {@link #getFieldValue()} equals to {@code fieldValue}.
     * @throws IllegalArgumentException when no value matched.
     */
    public static JinahyaColorSpaceType valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(JinahyaColorSpaceType.class, fieldValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    JinahyaColorSpaceType(final int fieldValue) {
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
