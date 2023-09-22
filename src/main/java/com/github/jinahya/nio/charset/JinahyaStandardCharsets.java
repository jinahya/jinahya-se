package com.github.jinahya.nio.charset;

import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public final class JinahyaStandardCharsets {

    /**
     * Returns an unmodifiable list of {@link Charset}s defined in {@link StandardCharsets} class.
     *
     * @return an unmodifiable list of {@link Charset}s defined in {@link StandardCharsets} class.
     */
    public static List<Charset> getStandardCharsetList() {
        return Arrays.stream(StandardCharsets.class.getFields())
                .filter(f -> {
                    final var modifiers = f.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers);
                })
                .filter(f -> f.getType() == Charset.class)
                .map(f -> {
                    try {
                        return (Charset) f.get(null);
                    } catch (final IllegalAccessException iae) {
                        throw new RuntimeException(iae);
                    }
                })
                .toList();
    }

    private JinahyaStandardCharsets() {
        super();
    }
}
