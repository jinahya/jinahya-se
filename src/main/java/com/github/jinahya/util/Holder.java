/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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

/**
 * A mutable holder of a single value.
 *
 * @param <T> value type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class Holder<T> {

    /**
     * Creates a new immutable holder whose {@link #set(Object)} method throws an
     * {@link UnsupportedOperationException}.
     *
     * @param <T>   value type parameter
     * @param value the value to hold.
     * @return a new immutable holder of {@code value}.
     */
    public static <T> Holder<T> newImmutableInstance(final T value) {

        return new Holder<T>(value) {

            @Override
            public void set(final T value) {

                throw new UnsupportedOperationException("immutable");
            }
        };
    }

    /**
     * Creates a new instance with specified value.
     *
     * @param value the initial value.
     */
    public Holder(final T value) {

        super();

        this.value = value;
    }

    /**
     * Returns the current value of this holder.
     *
     * @return the current value.
     */
    public T get() {

        return value;
    }

    /**
     * Replaces the current value of this holder with specified value.
     *
     * @param value the new value.
     */
    public void set(final T value) {

        this.value = value;
    }

    private T value;
}
