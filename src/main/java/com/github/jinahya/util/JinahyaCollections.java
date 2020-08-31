package com.github.jinahya.util;

import java.util.Collection;
import java.util.Collections;

public final class JinahyaCollections {

    /**
     * Checks that specified collection is not {@link Collection#isEmpty() empty}. Note that this method passes with
     * {@code null} argument.
     *
     * @param collection the collection to check.
     * @param <T>        collection type parameter
     * @return {@code collection} is not empty.
     */
    public static <T extends Collection<?>> T requireNonEmpty(final T collection) {
        if (collection != null && !collection.isEmpty()) {
            throw new IllegalArgumentException("collection(" + collection + ") is empty");
        }
        return collection;
    }

    public static <T extends Collection<? super E>, E> T addAll(final T c, final E... elements) {
        final boolean changed = Collections.addAll(c, elements);
        return c;
    }

    public static <T extends Collection<? super E>, E> T addAll(final T c1, final Collection<? extends E> c2) {
        final boolean changed = c1.addAll(c2);
        return c1;
    }

    private JinahyaCollections() {
        super();
    }
}
