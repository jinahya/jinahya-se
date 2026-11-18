/*
 * Copyright 2014 Jin Kwon.
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
package com.github.jinahya.util.function;

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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Utilities for {@link Supplier}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Suppliers {

    /**
     * Returns a supplier which caches the result of the first invocation of specified supplier and returns it for all
     * subsequent invocations.
     *
     * @param <T>     the type of results supplied by specified supplier.
     * @param wrappee the supplier whose result is cached.
     * @return a supplier which caches the result of {@code wrappee}.
     */
    public static <T> Supplier<T> cacheable(final Supplier<T> wrappee) {

        final List<T> holder = new ArrayList<>(1);

        final Supplier<T> wrapper = () -> {
            if (holder.isEmpty()) {
                holder.add(wrappee.get());
            }
            return holder.get(0);
        };

        return wrapper;
    }

    /**
     * Returns given supplier.
     *
     * @param <T>      the type of results supplied by specified supplier.
     * @param supplier the supplier.
     * @return the supplier.
     */
    public static <T> Supplier<T> of(final Supplier<T> supplier) {

        return supplier;
    }

    /**
     * Returns a supplier which always supplies specified value.
     *
     * @param <T>      the type of the value.
     * @param supplied the value to supply; may be {@code null}.
     * @return a supplier which always supplies {@code supplied}.
     */
    public static <T> Supplier<T> supplying(final T supplied) {

        return of(() -> supplied);
    }

    /**
     * Returns a supplier which always supplies {@code null}.
     *
     * @param <T> the type of results supplied.
     * @return a supplier which always supplies {@code null}.
     */
    public static <T> Supplier<T> supplyingNull() {

        return supplying(null);
    }

    private Suppliers() {

        super();
    }
}
