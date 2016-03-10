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
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <E> enum type parameter
 */
public interface FloatFieldEnum<E extends Enum<E>> {

    static <E extends Enum<E> & FloatFieldEnum<E>> float[] fieldValues(
            final Class<E> type) {
        if (type == null) {
            throw new NullPointerException("null type");
        }
        final E[] enumConstants = type.getEnumConstants();
        final float[] fieldValues = new float[enumConstants.length];
        for (int i = 0; i < fieldValues.length; i++) {
            fieldValues[i] = enumConstants[i].fieldValueAsFloat();
        }
        return fieldValues;
    }

    /**
     * Returns the field value as {@code float} type.
     *
     * @return the field value as {@code float} type.
     */
    float fieldValueAsFloat();

}
