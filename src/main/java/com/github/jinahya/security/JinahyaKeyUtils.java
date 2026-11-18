package com.github.jinahya.security;

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

import com.github.jinahya.util._ExcludeFromCoverage_PrivateConstructor_Obviously;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.security.spec.EncodedKeySpec;
import java.util.Objects;
import java.util.function.Function;

/**
 * A utility class for {@link Key}.
 */
public final class JinahyaKeyUtils {

    /**
     * Writes the encoded form of specified key to specified path.
     *
     * @param key          the key to write.
     * @param specFunction a function which creates an encoded key spec from the encoded form of the {@code key}.
     * @param path         the path to which the encoded key is written.
     * @throws IOException if an I/O error occurs.
     */
    public static void writeEncoded(
            final Key key, final Function<? super byte[], ? extends EncodedKeySpec> specFunction,
            final Path path)
            throws IOException {
        Objects.requireNonNull(key, "key is null");
        Objects.requireNonNull(specFunction, "specFunction is null");
        Objects.requireNonNull(path, "path is null");
        final var encoded = key.getEncoded(); // nullable
        final var keySpec = specFunction.apply(encoded);
        final var encodedKey = keySpec.getEncoded();
        Files.write(path, encodedKey);
    }

    /**
     * Reads an encoded key from specified path.
     *
     * @param <K>          key type parameter
     * @param path         the path from which the encoded key is read.
     * @param specFunction a function which creates an encoded key spec from bytes read.
     * @param keyFunction  a function which creates a key from the encoded key spec.
     * @return the key read.
     * @throws IOException if an I/O error occurs.
     */
    public static <K extends Key> K readEncoded(
            final Path path,
            final Function<? super byte[], ? extends EncodedKeySpec> specFunction,
            final Function<? super EncodedKeySpec, ? extends K> keyFunction)
            throws IOException {
        Objects.requireNonNull(path, "path is null");
        Objects.requireNonNull(specFunction, "specFunction is null");
        Objects.requireNonNull(keyFunction, "keyFunction is null");
        final var encodedKey = Files.readAllBytes(path);
        final var keySpec = specFunction.apply(encodedKey);
        return keyFunction.apply(keySpec);
    }

    @_ExcludeFromCoverage_PrivateConstructor_Obviously
    private JinahyaKeyUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
