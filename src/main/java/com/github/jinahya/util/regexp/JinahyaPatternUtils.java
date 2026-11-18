package com.github.jinahya.util.regexp;

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
import java.util.regex.Pattern;

/**
 * Utilities for {@link Pattern}s.
 */
public abstract class JinahyaPatternUtils {

    /**
     * Checks whether specified input entirely matches specified pattern.
     *
     * @param pattern the pattern to match.
     * @param input   the character sequence to be matched.
     * @return {@code true} if the entire {@code input} matches {@code pattern}; {@code false} otherwise.
     * @throws NullPointerException if {@code pattern} or {@code input} is {@code null}.
     */
    public static boolean matches(final Pattern pattern, final CharSequence input) {
        Objects.requireNonNull(pattern, "pattern is null");
        return pattern.matcher(input).matches();
    }

    /**
     * Creates a new instance.
     */
    protected JinahyaPatternUtils() {
        super();
    }
}
