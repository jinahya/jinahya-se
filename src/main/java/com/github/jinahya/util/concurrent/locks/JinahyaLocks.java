package com.github.jinahya.util.concurrent.locks;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;

/**
 * Utilities for {@link Lock}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class JinahyaLocks {

    /**
     * {@link Lock#tryLock() Tries to acquire} specified lock, applies the acquisition result to specified function, and
     * returns the result.
     *
     * @param <T>      lock type parameter
     * @param lock     the lock to acquire.
     * @param function the function to be applied with the acquisition result.
     * @return the result of the {@code function}.
     * @see Lock#tryLock()
     * @see Function#apply(Object)
     */
    public static <T extends Lock, R> R tryLockAndApply(
            final T lock, final BiFunction<? super T, ? super Boolean, ? extends R> function) {
        Objects.requireNonNull(lock, "lock is null");
        Objects.requireNonNull(function, "function is null");
        if (lock.tryLock()) {
            try {
                return function.apply(lock, Boolean.TRUE);
            } finally {
                lock.unlock();
            }
        }
        return function.apply(lock, Boolean.FALSE);
    }

    public static <T extends Lock> int tryLockAndApplyAsInt(
            final T lock, final ToIntBiFunction<? super T, ? super Boolean> function) {
        Objects.requireNonNull(function, "function is null");
        return tryLockAndApply(lock, function::applyAsInt);
    }

    public static <T extends Lock> long tryLockAndApplyAsLong(
            final T lock, final ToLongBiFunction<? super T, ? super Boolean> function) {
        Objects.requireNonNull(function, "function is null");
        return tryLockAndApply(lock, function::applyAsLong);
    }

    /**
     * {@link Lock#tryLock(long, TimeUnit) Tries to acquire} specified lock within given waiting time, applies the
     * acquisition result to specified function, and returns the result.
     *
     * @param <T>      lock type parameter
     * @param lock     the lock to acquire.
     * @param time     the maximum time to wait for the lock.
     * @param unit     the time unit of the time argument
     * @param function the function to be applied with the acquisition result.
     * @return the result of the {@code function}.
     * @throws InterruptedException if the current thread is interrupted while acquiring the lock (and interruption of
     *                              lock acquisition is supported)
     * @see Lock#tryLock(long, TimeUnit)
     */
    public static <T extends Lock, R> R tryLockAndApply(
            final T lock, final long time, final TimeUnit unit,
            final BiFunction<? super T, ? super Boolean, ? extends R> function)
            throws InterruptedException {
        Objects.requireNonNull(lock, "lock is null");
        Objects.requireNonNull(function, "function is null");
        if (lock.tryLock(time, unit)) {
            try {
                return function.apply(lock, Boolean.TRUE);
            } finally {
                lock.unlock();
            }
        }
        return function.apply(lock, Boolean.FALSE);
    }

    /**
     * {@link Lock#tryLock(long, TimeUnit) Tries to acquire} specified lock within given waiting time, applies the
     * acquisition result to specified function, and returns the result.
     *
     * @param <T>      lock type parameter
     * @param lock     the lock to acquire.
     * @param time     the maximum time to wait for the lock.
     * @param unit     the time unit of the time argument
     * @param function the function to be applied with the acquisition result.
     * @return the result of the {@code function}.
     * @throws InterruptedException if the current thread is interrupted while acquiring the lock (and interruption of
     *                              lock acquisition is supported)
     * @see Lock#tryLock(long, TimeUnit)
     */
    public static <T extends Lock> int tryLockAndApplyAsInt(final T lock, final long time, final TimeUnit unit,
                                                            final ToIntBiFunction<? super T, ? super Boolean> function)
            throws InterruptedException {
        Objects.requireNonNull(function, "function is null");
        return tryLockAndApply(lock, time, unit, function::applyAsInt);
    }

    /**
     * {@link Lock#tryLock(long, TimeUnit) Tries to acquire} specified lock within given waiting time, applies the
     * acquisition result to specified function, and returns the result.
     *
     * @param <T>      lock type parameter
     * @param lock     the lock to acquire.
     * @param time     the maximum time to wait for the lock.
     * @param unit     the time unit of the time argument
     * @param function the function to be applied with the acquisition result.
     * @return the result of the {@code function}.
     * @throws InterruptedException if the current thread is interrupted while acquiring the lock (and interruption of
     *                              lock acquisition is supported)
     * @see Lock#tryLock(long, TimeUnit)
     */
    public static <T extends Lock> long tryLockAndApplyAsLong(
            final T lock, final long time, final TimeUnit unit,
            final ToLongBiFunction<? super T, ? super Boolean> function)
            throws InterruptedException {
        Objects.requireNonNull(function, "function is null");
        return tryLockAndApply(lock, time, unit, function::applyAsLong);
    }

    private JinahyaLocks() {
        throw new AssertionError("instantiation is not allowed");
    }
}
