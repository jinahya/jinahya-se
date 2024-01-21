package com.github.jinahya.beans;

/*-
 * #%L
 * verbose-hello-world-srv-common
 * %%
 * Copyright (C) 2018 - 2022 Jinahya, Inc.
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
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A utility class for {@link java.beans} package.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class JinahyaBeansUtils2 {

    // -----------------------------------------------------------------------------------------------------------------
    private static <T, A> void acceptEachPropertyDescriptor(
            final Class<? super T> clazz, final T bean, final A attachment,
            final Function<? super T,
                    ? extends Function<? super A,
                            ? extends Consumer<? super PropertyDescriptor>>> function)
            throws IntrospectionException {
        Objects.requireNonNull(clazz, "clazz is null");
        Objects.requireNonNull(bean, "bean is null");
        Objects.requireNonNull(function, "function is null");
        JinahyaBeanInfoUtils.acceptEachPropertyDescriptor(clazz, d -> {
            function.apply(bean)
                    .apply(attachment)
                    .accept(d);
        });
    }

    private static <T, A> void acceptEachPropertyDescriptorHelper(
            final Class<T> clazz, final Object bean, final A attachment,
            final Function<? super T,
                    ? extends Function<? super A,
                            ? extends Consumer<? super PropertyDescriptor>>> function)
            throws IntrospectionException {
        acceptEachPropertyDescriptor(
                clazz,
                clazz.cast(bean),
                attachment,
                function
        );
    }

    @SuppressWarnings({
            "unchecked"
    })
    public static <T, A> void acceptEachPropertyDescriptor(
            final T bean, final A attachment,
            final Function<? super T,
                    ? extends Function<? super A,
                            ? extends Consumer<? super PropertyDescriptor>>> function)
            throws IntrospectionException {
        Objects.requireNonNull(bean, "bean is null");
        acceptEachPropertyDescriptorHelper(
                (Class<T>) bean.getClass(),
                bean,
                attachment,
                function
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({
            "java:S3011" // reader.setAccessible(true)
    })
    private static <T, A> void acceptEachPropertyValue(final Class<? super T> clazz, final T bean, final A attachment,
                                                       final Function<? super T,
                                                               ? extends Function<? super A,
                                                                       ? extends Function<? super PropertyDescriptor,
                                                                               ? extends Consumer<Object>>>> function)
            throws IntrospectionException {
        acceptEachPropertyDescriptor(clazz, bean, attachment, b -> a -> d -> {
            final var reader = d.getReadMethod();
            if (reader == null || reader.getDeclaringClass() != clazz) {
                return;
            }
            if (!reader.canAccess(bean)) {
                reader.setAccessible(true);
            }
            Object value;
            try {
                value = reader.invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("failed to invoke reader(" + reader + ") on " + bean, e);
            }
            final var type = d.getPropertyType(); // may be null
            if (type != null && type.isArray() && !type.getComponentType().isPrimitive()) {
                value = Arrays.asList((Object[]) value);
            }
            if (value instanceof Enumeration<?> e) {
                value = Collections.list(e);
            }
            if (value instanceof Stream<?> s) {
                value = s.toList();
            }
            function.apply(bean)
                    .apply(attachment)
                    .apply(d)
                    .accept(value);
        });
    }

    private static <T, A> void acceptEachPropertyValueHelper(
            final Class<T> clazz, final Object bean, final A attachment,
            final Function<? super T,
                    ? extends Function<? super A,
                            ? extends Function<? super PropertyDescriptor,
                                    ? extends Consumer<Object>>>> function)
            throws IntrospectionException {
        acceptEachPropertyValue(
                clazz,
                clazz.cast(bean),
                attachment,
                function
        );
    }

    @SuppressWarnings({
            "unchecked"
    })
    public static <T, A> void acceptEachPropertyValue(final T bean, final A attachment,
                                                      final Function<? super T,
                                                              ? extends Function<? super A,
                                                                      ? extends Function<? super PropertyDescriptor,
                                                                              ? extends Consumer<Object>>>> function)
            throws IntrospectionException {
        Objects.requireNonNull(bean, "bean is null");
        acceptEachPropertyValueHelper(
                (Class<T>) bean.getClass(),
                bean,
                attachment,
                function
        );
    }

    private JinahyaBeansUtils2() {
        throw new AssertionError("instantiation is not allowed");
    }
}