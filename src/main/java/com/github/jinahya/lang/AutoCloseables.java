/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.lang;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Consumer;

/**
 * Utilities for {@link AutoCloseable}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class AutoCloseables {

    private static final Method CLOSE;

    static {
        try {
            CLOSE = AutoCloseable.class.getMethod("close");
        } catch (final NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }

    /**
     * Creates a proxy of {@code AutoCloseable} for given object reference.
     *
     * @param <T> the type of the instance.
     * @param loader the classloader.
     * @param instance the instance.
     * @param closer the consumer for {@link AutoCloseable#close()}.
     *
     * @return a proxy of {@code AutoCloseable}.
     *
     * @see AutoCloseable
     * @see Proxy#newProxyInstance(java.lang.ClassLoader, java.lang.Class<?>[],
     * java.lang.reflect.InvocationHandler)
     */
    public static <T> AutoCloseable of(final ClassLoader loader,
                                       final T instance,
                                       final Consumer<T> closer) {
        return (AutoCloseable) Proxy.newProxyInstance(
                loader,
                new Class<?>[]{AutoCloseable.class},
                (proxy, method, args) -> {
                    if (CLOSE.equals(method)) {
                        closer.accept(instance);
                        return null;
                    }
                    return method.invoke(instance, args);
                });
    }

    /**
     * Creates a new proxy of {@link AutoCloseable} for given object reference.
     *
     * @param <T> instance type parameter
     * @param instance the instance
     * @param closer the consumer for {@link AutoCloseable#close()}.
     * @return a new proxy instance.
     *
     * @see #of(java.lang.ClassLoader, java.lang.Object,
     * java.util.function.Consumer)
     */
    public static <T> AutoCloseable of(final T instance,
                                       final Consumer<T> closer) {
        return of(instance.getClass().getClassLoader(), instance, closer);
    }

    private AutoCloseables() {
        super();
    }
}
