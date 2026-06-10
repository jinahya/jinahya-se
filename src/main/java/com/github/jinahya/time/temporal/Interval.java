package com.github.jinahya.time.temporal;

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

import java.io.Serial;
import java.io.Serializable;
import java.time.temporal.Temporal;
import java.util.Objects;

/**
 * Represents a time interval of specific temporal type.
 *
 * @param <T> temporal type parameter
 * @see <a href="https://www.joda.org/joda-time/apidocs/org/joda/time/Interval.html">org.joda.time.Interval</a>
 */
public final class Interval<T extends Temporal & Comparable<? super T>>
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -7195401716791497138L;

    // -----------------------------------------------------------------------------------------------------------------
    public Interval(final T start, final T end) {
        super();
        Objects.requireNonNull(start, "start is null");
        Objects.requireNonNull(end, "end is null");
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("start(" + start + ") must be before the end(" + end + ")");
        }
        this.start = start;
        this.end = end;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + '{' +
               "start=" + start +
               ",end=" + end +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var that = (Interval<?>) obj;
        return Objects.equals(start, that.start) &&
               Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    // ----------------------------------------------------------------------------------------------------------- start
    public T getStart() {
        return start;
    }

    // ------------------------------------------------------------------------------------------------------------- end
    public T getEnd() {
        return end;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final T start;

    private final T end;
}
