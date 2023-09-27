package com.github.jinahya.nio.channels;

import java.nio.channels.SelectionKey;
import java.util.Objects;

public final class JinahyaSelectionKeys {

    public static boolean isAny(final SelectionKey selectionKey, final int interestOp, final int... otherInterestOps) {
        Objects.requireNonNull(selectionKey, "selectionKey is null");
        final int interestOps = selectionKey.interestOps();
        if ((interestOps & interestOp) == interestOp) {
            return true;
        }
        for (final int otherInterestOp : otherInterestOps) {
            if ((interestOps & otherInterestOp) == otherInterestOp) {
                return true;
            }
        }
        return false;
    }

    private JinahyaSelectionKeys() {
        throw new AssertionError("instantiation is not allowed");
    }
}
