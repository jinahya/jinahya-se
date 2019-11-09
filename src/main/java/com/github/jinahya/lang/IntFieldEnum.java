/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
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
 */
package com.github.jinahya.lang;

/**
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface IntFieldEnum<E extends Enum<E>> {

    // -----------------------------------------------------------------------------------------------------------------
    static <E extends Enum<E> & IntFieldEnum<E>> int[] fieldValues(final Class<E> enumType) {
        final E[] enumConstants = enumType.getEnumConstants();
        final int[] fieldValues = new int[enumConstants.length];
        for (int i = 0; i < fieldValues.length; i++) {
            fieldValues[i] = enumConstants[i].getFieldValue();
        }
        return fieldValues;
    }

    // -----------------------------------------------------------------------------------------------------------------
    static <E extends Enum<E> & IntFieldEnum<E>> E valueOfFieldValue(final Class<E> enumType, final int fieldValue) {
        for (final E enumConstant : enumType.getEnumConstants()) {
            if (enumConstant.getFieldValue() == fieldValue) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("no value for field value: " + fieldValue);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the defined field value as an {@code int}.
     *
     * @return the defined field value as an {@code int}.
     */
    int getFieldValue();
}
