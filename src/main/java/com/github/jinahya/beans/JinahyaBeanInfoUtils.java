package com.github.jinahya.beans;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Objects;
import java.util.function.Consumer;

public final class JinahyaBeanInfoUtils {

    /**
     * Accepts all property descriptors, of specified class, to specified consumer, one by one.
     *
     * @param clazz    the class whose property descriptors are accepted.
     * @param consumer the consumer accepts each property descriptor.
     * @throws IntrospectionException if failed to introspect.
     * @see Introspector#getBeanInfo(Class)
     */
    public static void acceptEachPropertyDescriptor(final Class<?> clazz,
                                                    final Consumer<? super PropertyDescriptor> consumer)
            throws IntrospectionException {
        Objects.requireNonNull(clazz, "clazz is null");
        Objects.requireNonNull(consumer, "consumer is null");
        final var propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
        if (propertyDescriptors == null) {
            return;
        }
        for (final var propertyDescriptor : propertyDescriptors) {
            consumer.accept(propertyDescriptor);
        }
    }

    JinahyaBeanInfoUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
