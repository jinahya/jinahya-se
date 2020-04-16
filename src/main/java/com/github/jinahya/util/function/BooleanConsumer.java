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
package com.github.jinahya.util.function;

/**
 * Represents an operation that accepts a single {@code boolean}-valued argument and returns no results. This is the
 * primitive type specialization of {@link java.util.function.Consumer} for {@code boolean}. Unlike most other
 * functional interfaces, {@code BooleanConsumer} is expected to operate via side-effects.
 * <p>
 * This is a
 * <a href="http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">functional
 * interface</a> whose functional method is {@link #accept(boolean)}.
 * <p>
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@FunctionalInterface
public interface BooleanConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input value
     */
    void accept(boolean value);

    /**
     * Returns a composed {@code BooleanConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If performing either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed LongConsumer that performs in sequence this operation followed by the after operation.
     * @throws NullPointerException if after is {@code null}
     */
    default BooleanConsumer andThen(final BooleanConsumer after) {

        return v -> {
            accept(v);
            after.accept(v);
        };
    }
}
