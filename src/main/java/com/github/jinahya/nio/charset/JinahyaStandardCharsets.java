package com.github.jinahya.nio.charset;

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

import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public final class JinahyaStandardCharsets {

    /**
     * Returns an unmodifiable list of {@link Charset}s defined in {@link StandardCharsets} class.
     *
     * @return an unmodifiable list of {@link Charset}s defined in {@link StandardCharsets} class.
     */
    public static List<Charset> getStandardCharsetList() {
        return Arrays.stream(StandardCharsets.class.getFields())
                .filter(f -> {
                    final var modifiers = f.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers);
                })
                .filter(f -> f.getType() == Charset.class)
                .map(f -> {
                    try {
                        return (Charset) f.get(null);
                    } catch (final IllegalAccessException iae) {
                        throw new RuntimeException(iae);
                    }
                })
                .toList();
    }

    private JinahyaStandardCharsets() {
        super();
    }
}
