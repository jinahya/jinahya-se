package com.github.jinahya.util.regexp;

import java.util.Objects;
import java.util.regex.Pattern;

public abstract class JinahyaPatternUtils {

    public static boolean matches(final Pattern pattern, final CharSequence input) {
        Objects.requireNonNull(pattern, "pattern is null");
        return pattern.matcher(input).matches();
    }

    protected JinahyaPatternUtils() {
        super();
    }
}
