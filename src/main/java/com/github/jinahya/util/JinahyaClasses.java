package com.github.jinahya.util;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public abstract class JinahyaClasses {

    /**
     * Traverses the class hierarchy from specified class through its superclasses while specified predicate tests
     * {@code true}.
     *
     * @param clazz     the class to start traversing.
     * @param predicate the predicate for testing classes.
     */
    public static void traverse(final Class<?> clazz, final Predicate<Class<?>> predicate) {
        requireNonNull(clazz, "clazz is null");
        requireNonNull(predicate, "predicate is null");
        for (Class<?> c = clazz; true; c = c.getSuperclass()) {
            if (c == null) {
                return;
            }
            if (!predicate.test(c)) {
                return;
            }
        }
    }

    protected JinahyaClasses() {
        super();
    }
}
