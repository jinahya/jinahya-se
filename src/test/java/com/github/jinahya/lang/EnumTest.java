package com.github.jinahya.lang;

import java.util.Objects;

public abstract class EnumTest<E extends Enum<E>> {

    protected EnumTest(final Class<E> enumClass) {
        super();
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
    }

    protected final Class<E> enumClass;
}
