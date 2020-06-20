package com.github.jinahya.util.function;

import java.util.function.Predicate;

/**
 * A utility class for {@link Predicate} interface.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class JinahyaPredicates {

    private static final Predicate<?> EVALUATING_TRUE = t -> true;

    private static final Predicate<?> EVALUATING_FALSE = t -> false;

    /**
     * Returns a predicate evaluates itself as {@code true} on any given argument.
     *
     * @param <T> the type of the input to the predicate
     * @return a predicate evaluates itself as {@code true} on any given argument.
     */
    @SuppressWarnings({"unchecked"})
    public static <T> Predicate<T> evaluatingTrue() {
        return (Predicate<T>) EVALUATING_TRUE;
    }

    /**
     * Returns a predicate evaluates itself as {@code true} on any given argument.
     *
     * @param <T> the type of the input to the predicate
     * @return a predicate evaluates itself as {@code true} on any given argument.
     */
    public static <T> Predicate<T> truth() {
        return evaluatingTrue();
    }

    /**
     * Returns a predicate evaluates itself as {@code false} on any given argument.
     *
     * @param <T> the type of the input to the predicate
     * @return a predicate evaluates itself as {@code true} on any given argument.
     */
    @SuppressWarnings({"unchecked"})
    public static <T> Predicate<T> evaluatingFalse() {
        return (Predicate<T>) EVALUATING_FALSE;
    }

    /**
     * Returns a predicate evaluates itself as {@code false} on any given argument.
     *
     * @param <T> the type of the input to the predicate
     * @return a predicate evaluates itself as {@code true} on any given argument.
     */
    public static <T> Predicate<T> falsity() {
        return evaluatingFalse();
    }

    private JinahyaPredicates() {
        throw new AssertionError("instantiation is not allowed");
    }
}
