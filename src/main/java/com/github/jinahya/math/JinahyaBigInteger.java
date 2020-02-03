package com.github.jinahya.math;

import java.math.BigInteger;

public final class JinahyaBigInteger {

    // -----------------------------------------------------------------------------------------------------------------
    public static BigInteger safeInstance(final BigInteger value) {
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        return value.getClass() == BigInteger.class ? value : new BigInteger(value.toByteArray());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaBigInteger() {
        super();
    }
}
