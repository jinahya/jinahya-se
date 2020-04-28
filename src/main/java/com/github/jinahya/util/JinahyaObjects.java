package com.github.jinahya.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class JinahyaObjects {

    /**
     * Checks that the specified object reference is not {@code null} and applies it to the specified function.
     *
     * @param obj      the object reference to check for nullability.
     * @param function the function to be applied with the {@code obj}.
     * @param <T>      the type of the reference
     * @param <R>      the type of the result of the {@code function}
     * @return the result of the function.
     * @see Objects#requireNonNull(Object)
     */
    public static <T, R> R requireNonNull(final T obj, final Function<? super T, ? extends R> function) {
        return Objects.requireNonNull(function, "function is null").apply(Objects.requireNonNull(obj));
    }

    public static <T, R> R requireNonNull(final T obj, final String message,
                                          final Function<? super T, ? extends R> function) {
        return Objects.requireNonNull(function, "function is null").apply(Objects.requireNonNull(obj, message));
    }

    public static <T, R> R requireNonNull(final T obj, final Supplier<String> messageSupplier,
                                          final Function<? super T, ? extends R> function) {
        return Objects.requireNonNull(function, "function is null").apply(Objects.requireNonNull(obj, messageSupplier));
    }

    public static <T> void requireNonNull(final T obj, final Consumer<? super T> consumer) {
        requireNonNull(obj, v -> {
            Objects.requireNonNull(consumer, "consumer is null").accept(v);
            return null;
        });
    }

    public static <T> void requireNonNull(final T obj, final String message,
                                          final Consumer<? super T> consumer) {
        requireNonNull(obj, message, v -> {
            Objects.requireNonNull(consumer, "consumer is null").accept(v);
            return null;
        });
    }

    public static <T> void requireNonNull(final T obj, final Supplier<String> messageSupplier,
                                          final Consumer<? super T> consumer) {
        requireNonNull(obj, messageSupplier, v -> {
            Objects.requireNonNull(consumer, "function is null").accept(v);
            return null;
        });
    }

    private JinahyaObjects() {
    }
}
