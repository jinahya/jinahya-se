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

import static java.util.Arrays.stream;

/**
 * An interface for defining an enum for long constant fields.
 *
 * @param <E> enum type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface LongFieldEnum<E extends Enum<E>> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns an array of field values of all constants of specified enum type, in declaration order.
     *
     * @param enumType the enum type.
     * @param <E>      enum type parameter
     * @return an array of field values of all constants of {@code enumType}.
     */
    static <E extends Enum<E> & LongFieldEnum<E>> long[] fieldValues(final Class<E> enumType) {
        return stream(enumType.getEnumConstants()).mapToLong(LongFieldEnum::getFieldValue).toArray();
    }

    /**
     * Returns the constant of specified enum type whose field value is equal to specified value.
     *
     * @param enumType   the enum type.
     * @param fieldValue the field value to match.
     * @param <E>        enum type parameter
     * @return the constant of {@code enumType} whose field value is equal to {@code fieldValue}.
     * @throws IllegalArgumentException if no constant matches {@code fieldValue}.
     */
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
