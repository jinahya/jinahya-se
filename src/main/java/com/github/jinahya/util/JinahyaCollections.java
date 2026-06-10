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

    public static <T extends Collection<? super E>, E> T addAll(final T c, final E... elements) {
        final boolean changed = Collections.addAll(c, elements);
        return c;
    }

    public static <T extends Collection<? super E>, E> T addAll(final T c1, final Collection<? extends E> c2) {
        final boolean changed = c1.addAll(c2);
        return c1;
    }

    private JinahyaCollections() {
        super();
    }
}
