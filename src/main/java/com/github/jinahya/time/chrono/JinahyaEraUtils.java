package com.github.jinahya.time.chrono;

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

import java.time.chrono.Era;
import java.time.temporal.TemporalField;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class JinahyaEraUtils {

    public static <T extends TemporalField> Stream<T> filterSupported(final Era era, final Iterable<T> fields) {
        Objects.requireNonNull(era, "era is null");
        Objects.requireNonNull(fields, "fields is null");
        return StreamSupport.stream(fields.spliterator(), false)
                .filter(era::isSupported);
    }

    public static <T extends Enum<T> & TemporalField> Stream<T> filterSupported(final Era era,
                                                                                final Class<T> enumClass) {
        Objects.requireNonNull(era, "era is null");
        Objects.requireNonNull(enumClass, "enumClass is null");
        return filterSupported(era, List.of(enumClass.getEnumConstants()));
    }

    private JinahyaEraUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
