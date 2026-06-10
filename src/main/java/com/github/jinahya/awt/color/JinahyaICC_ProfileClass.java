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

import java.awt.color.ICC_Profile;

public enum JinahyaICC_ProfileClass
        implements IntFieldEnum<JinahyaICC_ProfileClass> {

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
    public static JinahyaICC_ProfileClass valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(JinahyaICC_ProfileClass.class, fieldValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    JinahyaICC_ProfileClass(final int fieldValue) {
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
