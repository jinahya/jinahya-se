package com.github.jinahya.beans;

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
