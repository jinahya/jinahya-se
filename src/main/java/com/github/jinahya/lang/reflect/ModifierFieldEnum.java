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
package com.github.jinahya.lang.reflect;

import com.github.jinahya.lang.IntFieldEnum;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <E> enum type parameter
 */
public interface ModifierFieldEnum<E extends Enum<E>> extends IntFieldEnum<E> {

    default boolean is(final int modifiers) {
        return (modifiers & fieldValueAsInt()) == fieldValueAsInt();
    }

    /**
     * Adds this constant's field value to specified value.
     *
     * @param modifiers the value this constant's modifier going to be added.
     *
     * @return new modifiers this constant's modifier added
     */
    default int add(final int modifiers) {
        return modifiers | fieldValueAsInt();
    }

    /**
     * Removes this constant's field value from the specified value.
     *
     * @param modifiers the value this constant's modifier going to be removed.
     *
     * @return new modifiers this constant's modifier removed.
     */
    default int remove(int modifiers) {
        return modifiers ^ fieldValueAsInt();
    }
}
