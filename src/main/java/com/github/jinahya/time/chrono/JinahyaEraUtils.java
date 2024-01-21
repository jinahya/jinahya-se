package com.github.jinahya.time.chrono;

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
