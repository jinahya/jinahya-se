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

/**
 * @param <T>
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class Holder<T> {

    public static <T> Holder<T> newImmutableInstance(final T value) {

        return new Holder<T>(value) {

            @Override
            public void set(final T value) {

                throw new UnsupportedOperationException("immutable");
            }
        };
    }

    public Holder(final T value) {

        super();

        this.value = value;
    }

    public T get() {

        return value;
    }

    public void set(final T value) {

        this.value = value;
    }

    private T value;
}
