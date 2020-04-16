package com.github.jinahya.util.function;

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