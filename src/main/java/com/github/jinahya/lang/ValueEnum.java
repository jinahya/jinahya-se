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

/**
 * An interface for enums whose constants are mapped to specific values.
 *
 * @param <E> enum type parameter
 * @param <V> value type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface ValueEnum<E extends Enum<E>, V> {

    /**
     * An interface for enums whose constants are mapped to {@code int} values.
     *
     * @param <E> enum type parameter
     */
    interface OfInt<E extends Enum<E> & OfInt<E>> {

        /**
         * Returns the value of this constant as an {@code int}.
         *
         * @return the value of this constant as an {@code int}.
         */
        int valueAsInt();
    }

    /**
     * An interface for enums whose constants are mapped to {@code long} values.
     *
     * @param <E> enum type parameter
     */
    interface OfLong<E extends Enum<E> & OfLong<E>> {

        /**
         * Returns the value of this constant as a {@code long}.
         *
         * @return the value of this constant as a {@code long}.
         */
        long valueAsLong();
    }

    /**
     * An interface for enums whose constants are mapped to {@code float} values.
     *
     * @param <E> enum type parameter
     */
    interface OfFloat<E extends Enum<E> & OfFloat<E>> {

        /**
         * Returns the value of this constant as a {@code float}.
         *
         * @return the value of this constant as a {@code float}.
         */
        float valueAsFloat();
    }

    /**
     * An interface for enums whose constants are mapped to {@code double} values.
     *
     * @param <E> enum type parameter
     */
    interface OfDouble<E extends Enum<E> & OfDouble<E>> {

        /**
         * Returns the value of this constant as a {@code double}.
         *
         * @return the value of this constant as a {@code double}.
         */
        double valueAsDouble();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the value of this constant.
     *
     * @return the value of this constant.
     */
    V value();
}
