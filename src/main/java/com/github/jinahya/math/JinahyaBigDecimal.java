package com.github.jinahya.math;

import java.math.BigDecimal;

public final class JinahyaBigDecimal {

    // -----------------------------------------------------------------------------------------------------------------
    public static BigDecimal safeInstance(final BigDecimal value) {
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        return null;
        // TODO: 2020/02/03 implement!!!
//        return value.getClass() == BigDecimal.class ? value : new BigDecimal(value.toBigIntegerExact());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaBigDecimal() {
        super();
    }
}
