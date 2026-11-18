package com.github.jinahya.nio.channels;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.nio.channels.SelectionKey;
import java.util.Objects;

/**
 * A utility class for {@link SelectionKey}.
 */
public final class JinahyaSelectionKeys {

    /**
     * Checks whether the interest set of specified selection key contains any of specified interest operations.
     *
     * @param selectionKey     the selection key to check.
     * @param interestOp       an interest operation.
     * @param otherInterestOps other interest operations.
     * @return {@code true} if the interest set of {@code selectionKey} contains any of specified interest operations;
     * {@code false} otherwise.
     * @see SelectionKey#interestOps()
     */
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
