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

/**
 * An interface for implementing an {@code Enum} for old school constant fields.
 *
 * @param <E> enum type parameter
 * @param <V> field type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface FieldEnum<E extends Enum<E>, V> {

    interface OfInt<E extends Enum<E> & OfInt<E>> {

        int fieldValueAsInt();
    }

    interface OfLong<E extends Enum<E> & OfLong<E>> {

        long fieldValueAsLong();
    }

    interface OfFloat<E extends Enum<E> & OfFloat<E>> {

        float fieldValueAsFloat();
    }

    /**
     * Returns field value.
     *
     * @return field value.
     */
    V fieldValue();
}
