/*
 * Copyright 2014 Jin Kwon.
 *
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
 */
package com.github.jinahya.nio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.InvalidMarkException;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * Utilities for {@link Buffer}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaBuffers {

    public static <T extends Buffer> T allocateZeroCapacity(
            final Class<T> type) {

        if (type == null) {
            throw new NullPointerException("null type");
        }

        try {
            final Method method = type.getMethod("allocate", Integer.TYPE);
            try {
                return type.cast(method.invoke(null, 0));
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException("failed to allocate", iae);
            } catch (final InvocationTargetException ite) {
                throw new RuntimeException("failed to allocate", ite);
            }
        } catch (final NoSuchMethodException nsme) {
            throw new RuntimeException("no allocation method found", nsme);
        }
    }

    public static final ByteBuffer ZERO_CAPACITY_BYTE_BUFFER
            = allocateZeroCapacity(ByteBuffer.class);

    public static final CharBuffer ZERO_CAPACITY_CHAR_BUFFER
            = allocateZeroCapacity(CharBuffer.class);

    public static final DoubleBuffer ZERO_CAPACITY_DOUBLE_BUFFER
            = allocateZeroCapacity(DoubleBuffer.class);

    public static final FloatBuffer ZERO_CAPACITY_FLOAT_BUFFER
            = allocateZeroCapacity(FloatBuffer.class);

    public static final IntBuffer ZERO_CAPACITY_INT_BUFFER
            = allocateZeroCapacity(IntBuffer.class);

    public static final LongBuffer ZERO_CAPACITY_LONG_BUFFER
            = allocateZeroCapacity(LongBuffer.class);

    public static final ShortBuffer ZERO_CAPACITY_SHORT_BUFFER
            = allocateZeroCapacity(ShortBuffer.class);

    public static <T extends Buffer> T requireNonZeroCapacity(final T buffer) {

        if (buffer == null) {
            throw new NullPointerException("null buffer");
        }

        if (buffer.capacity() == 0) {
            throw new IllegalArgumentException("buffer.capacity == 0");
        }

        return buffer;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T clear(final T buffer) {

        return (T) buffer.clear();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T flip(final T buffer) {

        return (T) buffer.flip();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T limit(final T buffer,
            final int newLimit) {

        return (T) buffer.limit(newLimit);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T mark(final T buffer) {

        return (T) buffer.mark();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T position(final T buffer,
            final int newPosition) {

        return (T) buffer.position(newPosition);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T reset(final T buffer) {

        return (T) buffer.reset();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T rewind(final T buffer) {

        return (T) buffer.rewind();
    }

    /**
     * The limit is set to current position and then the position to the
     * previously-marked position.
     *
     * @param <T> buffer type parameter.
     * @param buffer the buffer.
     *
     * @return
     */
    public static <T extends Buffer> T flipToMark(final T buffer) {

        if (buffer == null) {
            throw new NullPointerException("null buffer");
        }

        return (T) buffer.limit(buffer.position()).reset();
    }

    /**
     * Returns the position of given buffer where the mark has been set.
     *
     * @param buffer the buffer
     *
     * @return the position of given buffer where the mark has been set or
     * {@code null} if the mark has not been set
     *
     * @see #markedPosition(java.nio.Buffer, java.lang.Integer)
     */
    public static Integer markedPosition(final Buffer buffer) {

        final int position = buffer.position();
        try {
            try {
                buffer.reset();
                return buffer.position();
            } catch (final InvalidMarkException ime) {
                return null;
            }
        } finally {
            buffer.position(position);
        }
    }

    /**
     * Restores marked position while preserving current position.
     *
     * @param <T> buffer type parameter
     * @param buffer the buffer
     * @param markedPosition the marked position
     *
     * @return given buffer
     *
     * @see #markedPosition(java.nio.Buffer)
     */
    public static <T extends Buffer> T markedPosition(
            final T buffer, final Integer markedPosition) {

        if (markedPosition != null) {
            final int position = buffer.position();
            buffer.position(markedPosition);
            buffer.mark();
            buffer.position(position);
        }

        return buffer;
    }

    /**
     * Adjusts given buffer's position with specified amount.
     *
     * @param <T> buffer type parameter
     * @param buffer the buffer
     * @param delta the amount to adjust
     *
     * @return given buffer.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Buffer> T adjustPosition(
            final T buffer, final int delta) {
        return (T) buffer.position(buffer.position() + delta);
    }

    private JinahyaBuffers() {

        super();
    }

}
