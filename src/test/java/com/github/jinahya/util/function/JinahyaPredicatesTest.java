package com.github.jinahya.util.function;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.util.function.JinahyaPredicates.falsity;
import static com.github.jinahya.util.function.JinahyaPredicates.truth;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class for testing {@link JinahyaPredicates} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class JinahyaPredicatesTest {

    /**
     * Assert the predicate from {@link JinahyaPredicates#truth()} evaluates itself as {@code true}.
     */
    @Test
    void testTruth() {
        assertTrue(truth().test(null));
    }

    /**
     * Asserts the predicate from {@link JinahyaPredicates#falsity()} evaluates itself as {@code false}.
     */
    @Test
    void testFalsity() {
        assertFalse(falsity().test(null));
    }
}