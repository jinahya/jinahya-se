/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
import java.util.Collection;

/**
 * A helper class for {@link FieldEnum}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class FieldEnums {

    /**
     * Returns an array containing the field values of specified enum type, in
     * the order those enum constants returned from {@code E.values()}
     *
     * @param <E> enum type parameter
     * @param <V> field value type parameter
     * @param enumType enum type
     * @param fieldType field type
     *
     * @return an array containing the field values of this enum type
     */
    public static <E extends Enum<E> & FieldEnum<E, V>, V> V[] fieldValues(
            final Class<E> enumType, final Class<V> fieldType) {

        if (enumType == null) {
            throw new NullPointerException("null enumtype");
        }

        if (fieldType == null) {
            throw new NullPointerException("null fieldType");
        }

        final E[] enumConstants = enumType.getEnumConstants();

        @SuppressWarnings("unchecked")
        final V[] fieldValues
                = (V[]) Array.newInstance(fieldType, enumConstants.length);

        for (int i = 0; i < fieldValues.length; i++) {
            fieldValues[i] = enumConstants[i].fieldValue();
        }

        return fieldValues;
    }

    /**
     * Adds all field values of given enum type to specified collection.
     *
     * @param <E> enum type parameter
     * @param <V> field value type parameter
     * @param enumType enum type
     * @param fieldValues the collection to which field values are added.
     */
    public static <E extends Enum<E> & FieldEnum<E, V>, V> void fieldValues(
            final Class<E> enumType, final Collection<? super V> fieldValues) {

        if (enumType == null) {
            throw new NullPointerException("null enumtype");
        }

        if (fieldValues == null) {
            throw new NullPointerException("null fieldValues");
        }

        for (final E enumConstant : enumType.getEnumConstants()) {
            fieldValues.add(enumConstant.fieldValue());
        }
    }

    /**
     * Returns the enum constant of specified enum type with specified field
     * value.
     *
     * @param <E> enum type parameter
     * @param <V> field value type parameter
     * @param enumType enum type
     * @param fieldValue field value
     *
     * @throws NullPointerException if {@code enumType} is {@code null}.
     * @throws IllegalArgumentException if the specified enum type has no
     * constant with the specified field value, or the specified class object
     * does not represent an enum type
     *
     * @return the mapped enum constant.
     */
    public static <E extends Enum<E> & FieldEnum<E, V>, V> E fromFieldValue(
            final Class<E> enumType, final V fieldValue) {

        if (enumType == null) {
            throw new NullPointerException("null enumtype");
        }

        if (fieldValue == null) {
            // ok
        }

        for (final E enumConstant : enumType.getEnumConstants()) {
            final V constantFieldValue = enumConstant.fieldValue();
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
