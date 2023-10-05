package com.github.jinahya.awt.color;

import java.awt.color.ICC_Profile;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

public final class JinahyaICC_ProfileUtils {

    public static String nameOfProfileClass(final int profileClass) {
        return Arrays.stream(ICC_Profile.class.getFields())
                .filter(f -> {
                    final int modifiers = f.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
                })
                .filter(f -> f.getType() == int.class)
                .filter(f -> f.getName().startsWith("CLASS_"))
                .filter(f -> {
                    try {
                        return f.getInt(null) == profileClass;
                    } catch (final IllegalAccessException iae) {
                        throw new RuntimeException("failed to get value of " + f, iae);
                    }
                })
                .findFirst()
                .map(Field::getName)
                .orElse(null)
                ;
    }

    public static String nameOfProfileClass(final ICC_Profile iccProfile) {
        Objects.requireNonNull(iccProfile, "iccProfile is null");
        return nameOfProfileClass(iccProfile.getProfileClass());
    }

    private JinahyaICC_ProfileUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
