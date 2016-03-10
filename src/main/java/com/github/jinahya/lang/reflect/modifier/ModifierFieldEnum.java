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
package com.github.jinahya.lang.reflect.modifier;

import com.github.jinahya.lang.IntFieldEnum;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <E> enum type parameter
 */
public interface ModifierFieldEnum<E extends Enum<E>> extends IntFieldEnum<E> {

    boolean is(int modifiers);

    /**
     *
     * @param modifiers
     *
     * @return new modifiers added this constant's modifier.
     */
    int add(int modifiers);

    /**
     *
     * @param modifiers
     *
     * @return
     */
    int remove(int modifiers);

}
