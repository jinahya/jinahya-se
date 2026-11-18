package com.github.jinahya.util;

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

import java.util.Collection;
import java.util.Collections;

/**
 * Utilities for {@link Collection}s.
 */
public final class JinahyaCollections {

    /**
     * Checks that specified collection is not {@link Collection#isEmpty() empty}. Note that this method passes with
     * {@code null} argument.
     *
     * @param collection the collection to check.
     * @param <T>        collection type parameter
     * @return {@code collection} is not empty.
     */
    public static <T extends Collection<?>> T requireNonEmpty(final T collection) {
        if (collection != null && !collection.isEmpty()) {
            throw new IllegalArgumentException("collection(" + collection + ") is empty");
        }
        return collection;
    }

    /**
     * Adds all specified elements to specified collection and returns the collection.
     *
     * @param <T>      collection type parameter
     * @param <E>      element type parameter
     * @param c        the collection to which elements are added.
     * @param elements the elements to add.
     * @return given {@code c}.
     * @see Collections#addAll(Collection, Object[])
     */
    public static <T extends Collection<? super E>, E> T addAll(final T c, final E... elements) {
        final boolean changed = Collections.addAll(c, elements);
        return c;
    }

    /**
     * Adds all elements of specified second collection to specified first collection and returns the first collection.
     *
     * @param <T> first collection type parameter
     * @param <E> element type parameter
     * @param c1  the collection to which elements are added.
     * @param c2  the collection whose elements are added.
     * @return given {@code c1}.
     * @see Collection#addAll(Collection)
     */
    public static <T extends Collection<? super E>, E> T addAll(final T c1, final Collection<? extends E> c2) {
        final boolean changed = c1.addAll(c2);
        return c1;
    }

    private JinahyaCollections() {
        super();
    }
}
