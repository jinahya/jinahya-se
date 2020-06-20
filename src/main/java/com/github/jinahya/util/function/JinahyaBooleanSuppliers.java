package com.github.jinahya.util.function;

import java.util.function.BooleanSupplier;

public final class JinahyaBooleanSuppliers {

    private static final BooleanSupplier SUPPLYING_TRUE = () -> true;

    private static final BooleanSupplier SUPPLYING_FALSE = () -> false;

    public static BooleanSupplier supplying(final boolean result) {
        return result ? SUPPLYING_TRUE : SUPPLYING_FALSE;
    }

    private JinahyaBooleanSuppliers() {
        throw new AssertionError("instantiation is not allowed");
    }
}
