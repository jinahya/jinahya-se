package com.github.jinahya.imageio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An abstract class for image features.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class ImageIoFeature {

    // -----------------------------------------------------------------------------------------------------------------
    static <T extends ImageIoFeature> List<T> list(final Class<T> featureClass, final String[] readerValues,
                                                   final String[] writerValues) {
        final Map<String, T> m = new HashMap<>();
        for (final String value : readerValues) {
            try {
                final T instance = featureClass.getConstructor().newInstance();
                instance.setReadable(true);
                instance.setValue(value);
                m.put(value, instance);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        for (final String value : writerValues) {
            m.computeIfAbsent(value, k -> {
                try {
                    final T instance = featureClass.getConstructor().newInstance();
                    instance.setValue(k);
                    return instance;
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            }).setWritable(true);
        }
        return new ArrayList<>(m.values());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString()
               + "{"
               + "readable=" + readable
               + ",writable=" + writable
               + ",value=" + value
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------
    boolean fieldsEqual(final Object obj) {
        final ImageIoFeature that = (ImageIoFeature) obj;
        if (!Objects.equals(this.value, that.value)) {
            return false;
        }
        if (this.readable != that.readable) {
            return false;
        }
        if (this.writable != that.writable) {
            return false;
        }
        return true;
    }

    /**
     * Indicates whether some other object is "equals to " this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageIoFeature)) {
            return false;
        }
        return fieldsEqual(obj);
    }

    private int hashCode1() {
        return Objects.hash(readable, writable, value);
    }

    private int hashCode2() {
        int result = Boolean.hashCode(readable);
        result = 31 * result + Boolean.hashCode(writable);
        result = 31 * result + (value == null ? 0 : value.hashCode());
        return result;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return hashCode2();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(final boolean readable) {
        this.readable = readable;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public boolean isWritable() {
        return writable;
    }

    public void setWritable(final boolean writable) {
        this.writable = writable;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private boolean readable;

    private boolean writable;

    private String value;
}