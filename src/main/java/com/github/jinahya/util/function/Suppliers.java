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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Suppliers {

    private static final Supplier<?> SUPPLYING_NULL = () -> null;

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

    public static <T> Supplier<T> supplying(final T value) {
        return () -> value;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> Supplier<T> supplyingNull() {
        return (Supplier<T>) SUPPLYING_NULL;
    }

    private Suppliers() {
        throw new AssertionError("instantiation is not allowed");
    }
}
