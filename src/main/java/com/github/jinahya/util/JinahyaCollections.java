package com.github.jinahya.util;

import java.util.Collection;
import java.util.Collections;

public final class JinahyaCollections {

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
