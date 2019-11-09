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

import static java.util.Arrays.stream;

/**
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface LongFieldEnum<E extends Enum<E>> {

    // -----------------------------------------------------------------------------------------------------------------
    static <E extends Enum<E> & LongFieldEnum<E>> long[] fieldValues(final Class<E> enumType) {
        return stream(enumType.getEnumConstants()).mapToLong(LongFieldEnum::getFieldValue).toArray();
    }

    static <E extends Enum<E> & LongFieldEnum<E>> E valueOfFieldValue(final Class<E> enumType, final long fieldValue) {
        return stream(enumType.getEnumConstants())
                .filter(v -> v.getFieldValue() == fieldValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no value for field value: " + fieldValue));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the defined field value as a {@code long}.
     *
     * @return the defined field value as a {@code long}
     */
    long getFieldValue();
}
