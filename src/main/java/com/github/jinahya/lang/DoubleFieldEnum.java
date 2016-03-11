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
 * An interface for defining an enum for double constant fields.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <E> enum type parameter
 */
public interface DoubleFieldEnum<E extends Enum<E>> {

    static <E extends Enum<E> & DoubleFieldEnum<E>> double[] doubleFieldValues(
            final Class<E> enumType) {
        final E[] enumConstants = enumType.getEnumConstants();
        final double[] fieldValues = new double[enumConstants.length];
        for (int i = 0; i < fieldValues.length; i++) {
            fieldValues[i] = enumConstants[i].fieldValueAsDouble();
        }
        return fieldValues;
    }

    static <E extends Enum<E> & DoubleFieldEnum<E>> E fromDoubleFieldValue(
            final Class<E> enumType, final double fieldValue) {
        for (final E enumConstant : enumType.getEnumConstants()) {
            final double constantFieldValue = enumConstant.fieldValueAsDouble();
            if (constantFieldValue == fieldValue) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("unknown fieldValue: " + fieldValue);
    }

    /**
     * Returns the defined field value as a {@code double}.
     *
     * @return the field value as a {@code double}.
     */
    double fieldValueAsDouble();

}
