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

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static com.github.jinahya.util.function.JinahyaConsumers.EMPTY;
import static com.github.jinahya.util.function.JinahyaConsumers.discarding;
import static com.github.jinahya.util.function.JinahyaConsumers.of;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * A class for testing {@link JinahyaConsumers} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class JinahyaConsumersTest {

    /**
     * Tests {@link JinahyaConsumers#EMPTY} constant.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    void testEmpty() {
        EMPTY.accept(null);
        EMPTY.accept(new Object());
    }

    /**
     * Tests {@link JinahyaConsumers#discarding()} method.
     */
    @Test
    void testDiscarding() {
        final Consumer<? super Object> consumer = discarding();
        consumer.accept(null);
        consumer.accept(new Object());
    }

    /**
     * Tests {@link JinahyaConsumers#of(Consumer)} method.
     */
    @Test
    void testOf() {
        final Consumer<Object> consumer = o -> {
        };
        assertSame(consumer, of(consumer));
    }
}
