/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.lang.reflect.Array;


/**
 * A helper class for {@link FieldEnum}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class FieldEnums {


    /**
     * Returns an array containing the field values of specified enum type, in
     * the order those enum constants returned from {@code E.values()}
     *
     * @param <E> enum type parameter
     * @param <F> field type parameter
     * @param enumType enum type
     * @param fieldType field type
     *
     * @return an array containing the field values of this enum type
     */
    public static <E extends Enum<E> & FieldEnum<E, F>, F> F[] fieldValues(
        final Class<E> enumType, final Class<F> fieldType) {

        if (enumType == null) {
            throw new NullPointerException("null enumtype");
        }

        if (fieldType == null) {
            throw new NullPointerException("null fieldType");
        }

        final E[] enumConstants = enumType.getEnumConstants();

        @SuppressWarnings("unchecked")
        final F[] fieldValues
            = (F[]) Array.newInstance(fieldType, enumConstants.length);

        for (int i = 0; i < fieldValues.length; i++) {
            fieldValues[i] = enumConstants[i].getFieldValue();
        }

        return fieldValues;
    }


    /**
     * Returns the enum constant of specified enum type with specified field
     * value.
     *
     * @param <E> enum type parameter
     * @param <F> field type parameter
     * @param enumType enum type
     * @param fieldValue field value
     *
     * @throws NullPointerException if {@code enumType} is null
     * @throws IllegalArgumentException if the specified enum type has no
     * constant with the specified field value, or the specified class object
     * does not represent an enum type
     *
     * @return the mapped enum constant.
     */
    public static <E extends Enum<E> & FieldEnum<E, F>, F> E fromFieldValue(
        final Class<E> enumType, final F fieldValue) {

        if (enumType == null) {
            throw new NullPointerException("null enumtype");
        }

        if (fieldValue == null) {
            // ok
        }

        for (final E enumConstant : enumType.getEnumConstants()) {
            final F constantFieldValue = enumConstant.getFieldValue();
            if (constantFieldValue == null
                ? fieldValue == null : constantFieldValue.equals(fieldValue)) {
                return enumConstant;
            }
        }

        throw new IllegalArgumentException("unknown fieldValue: " + fieldValue);
    }


    /**
     * Creates a new instance.
     */
    private FieldEnums() {

        super();
    }


}
