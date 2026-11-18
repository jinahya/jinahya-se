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
package com.github.jinahya.util;

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

/**
 * Utilities for {@link String}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Strings {

    /**
     * Checks whether specified string is empty.
     *
     * @param string the string to check.
     * @return {@code true} if {@code string} is empty; {@code false} otherwise.
     * @throws NullPointerException if {@code string} is {@code null}.
     */
    public static boolean isEmpty(final String string) {

        return string.isEmpty();
    }

    /**
     * Checks whether specified string is not empty.
     *
     * @param string the string to check.
     * @return {@code true} if {@code string} is not empty; {@code false} otherwise.
     * @throws NullPointerException if {@code string} is {@code null}.
     */
    public static boolean nonEmpty(final String string) {

        return !isEmpty(string);
    }

    /**
     * Checks whether specified string is empty after being {@link String#trim() trimmed}.
     *
     * @param string the string to check.
     * @return {@code true} if trimmed {@code string} is empty; {@code false} otherwise.
     * @throws NullPointerException if {@code string} is {@code null}.
     */
    public static boolean isTrimmedEmpty(final String string) {
        return string.trim().isEmpty();
    }

    /**
     * Checks whether specified string is not empty after being {@link String#trim() trimmed}.
     *
     * @param string the string to check.
     * @return {@code true} if trimmed {@code string} is not empty; {@code false} otherwise.
     * @throws NullPointerException if {@code string} is {@code null}.
     */
    public static boolean nonTrimmedEmpty(final String string) {
        return !isTrimmedEmpty(string);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Strings() {
        super();
    }
}
