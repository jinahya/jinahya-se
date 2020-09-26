package com.github.jinahya.util;

import java.util.Objects;

public final class JinahyaStrings {

    public static boolean nonEmpty(final String str) {
        return Objects.requireNonNull(str, "str is null").isEmpty();
    }

//    public static boolean nonBlank(final String str) {
//        return Objects.requireNonNull(str, "str is null").isBlank();
//    }

    private JinahyaStrings() {
        super();
    }
}
