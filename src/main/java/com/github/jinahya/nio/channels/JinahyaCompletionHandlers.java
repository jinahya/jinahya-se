package com.github.jinahya.nio.channels;

import java.nio.channels.CompletionHandler;
import java.util.Objects;

public final class JinahyaCompletionHandlers {

    @FunctionalInterface
    public interface OnCompleted<V, A> {

        void completed(V result, A attachment);
    }

    @FunctionalInterface
    public interface OnFailed<A> {

        void failed(Throwable exc, A attachment);
    }

    public static <V, A> CompletionHandler<V, A> of(final OnCompleted<? super V, ? super A> onCompleted,
                                                    final OnFailed<? super A> onFailed) {
        Objects.requireNonNull(onCompleted, "onCompleted is null");
        Objects.requireNonNull(onFailed, "onFailed is null");
        return new CompletionHandler<V, A>() {
            @Override
            public void completed(final V result, final A attachment) {
                onCompleted.completed(result, attachment);
            }

            @Override
            public void failed(final Throwable exc, final A attachment) {
                onFailed.failed(exc, attachment);
            }
        };
    }

    private JinahyaCompletionHandlers() {
        throw new AssertionError("instantiation is not allowed");
    }
}
