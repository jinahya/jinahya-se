package com.github.jinahya.imageio;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An abstract class for image features.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class ImageIoFeature {

    static <T extends ImageIoFeature> List<T> list(final Class<T> featureClass, final Set<String> readerValues,
                                                   final Set<String> writerValues) {
        Objects.requireNonNull(featureClass, "featureClass is null");
        Objects.requireNonNull(readerValues, "readerValues is null");
        Objects.requireNonNull(writerValues, "writerValues is null");
        final Constructor<T> constructor;
        try {
            constructor = featureClass.getDeclaredConstructor(String.class, boolean.class, boolean.class);
        } catch (final NoSuchMethodException nsme) {
            throw new RuntimeException("failed to find constructor for " + String.class + ", " + boolean.class + ", "
                                       + boolean.class + " from " + featureClass, nsme);
        }
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return Stream.concat(readerValues.stream(), writerValues.stream())
                .distinct()
                .map(v -> {
                    final boolean readable = readerValues.contains(v);
                    final boolean writable = writerValues.contains(v);
                    try {
                        return constructor.newInstance(v, readable, writable);
                    } catch (final ReflectiveOperationException roe) {
                        throw new RuntimeException("failed to instantiate via " + constructor, roe);
                    }
                })
                .collect(Collectors.toList());
    }

    ImageIoFeature(final String value, final boolean readable, final boolean writable) {
        super();
        this.value = Objects.requireNonNull(value, "value is null");
        this.readable = readable;
        this.writable = writable;
    }

    @Override
    public String toString() {
        return super.toString() + '{' +
               "value=" + value +
               ",readable=" + readable +
               ",writable=" + writable +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ImageIoFeature)) return false;
        final ImageIoFeature that = (ImageIoFeature) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * Returns the value of this feature.
     *
     * @return the value of this feature.
     */
    public final String getValue() {
        return value;
    }

    /**
     * Returns the flag of read capability of this feature.
     *
     * @return {@code true} if readable; {@code false} otherwise.
     */
    public final boolean isReadable() {
        return readable;
    }

    /**
     * Returns the flag of write capability of this feature.
     *
     * @return {@code true} if writable; {@code false} otherwise.
     */
    public final boolean isWritable() {
        return writable;
    }

    private final String value;

    private final boolean readable;

    private final boolean writable;
}
