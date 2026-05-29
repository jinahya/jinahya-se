package com.github.jinahya.lang;

import java.io.IOException;

public final class JinahyaAppendableUtils {

    @SuppressWarnings({"unchecked"})
    public static <T extends Appendable> T append(final T appendable, final char c) throws IOException {
        return (T) appendable.append(c);
    }

    private JinahyaAppendableUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
