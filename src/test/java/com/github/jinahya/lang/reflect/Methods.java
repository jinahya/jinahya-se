/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.lang.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Methods {

    /**
     * Invokes specified method, on the specified object with the specified parameters while wrapping any checked
     * exceptions to instances of {@link RuntimeException}.
     *
     * @param method the method to invoke
     * @param obj    the object the underlying method is invoked from
     * @param args   the arguments used for the method call
     * @return the result of dispatching the {@code method} on {@code obj} with parameters {@code args}.
     * @throws NullPointerException if {@code method} is {@code null}
     * @see Method#invoke(java.lang.Object, java.lang.Object...)
     */
    public static Object invokeUnchecked(final Method method, final Object obj,
                                         final Object... args) {

        if (method == null) {
            throw new NullPointerException("null method");
        }

        try {
            return method.invoke(obj, args);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (final InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (RuntimeException.class.isInstance(cause)) {
                throw RuntimeException.class.cast(cause);
            }
            throw new RuntimeException(ite);
        }
    }

    private Methods() {

        super();
    }
}
