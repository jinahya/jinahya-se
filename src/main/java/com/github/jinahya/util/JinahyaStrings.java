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

import java.util.Objects;

/**
 * Utilities for {@link String}s.
 */
public final class JinahyaStrings {

    /**
     * Checks the emptiness of specified string.
     *
     * @param str the string to check.
     * @return the value of {@link String#isEmpty() str.isEmpty()}.
     * @throws NullPointerException if {@code str} is {@code null}.
     * @implNote Despite its name, this method currently returns {@code true} when {@code str} is empty.
     */
    public static boolean nonEmpty(final String str) {
        return Objects.requireNonNull(str, "str is null").isEmpty();
    }

//    public static boolean nonBlank(final String str) {
//        return Objects.requireNonNull(str, "str is null").isBlank();
//    }

    private JinahyaStrings() {
        super();
    }
}
