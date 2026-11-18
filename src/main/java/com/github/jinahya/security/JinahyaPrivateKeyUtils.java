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
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.function.Function;

/**
 * A utility class for {@link PrivateKey}.
 */
public final class JinahyaPrivateKeyUtils {

    private static final Function<byte[], EncodedKeySpec> PKCS8 = PKCS8EncodedKeySpec::new;

    /**
     * Writes the encoded form of specified private key to specified path.
     *
     * @param key      the private key to write.
     * @param function a function which creates an encoded key spec from the encoded form of the {@code key}.
     * @param path     the path to which the encoded key is written.
     * @throws IOException if an I/O error occurs.
     */
    public static void writeEncoded(
            final PrivateKey key,
            final Function<? super byte[], ? extends EncodedKeySpec> function,
            final Path path)
            throws IOException {
        JinahyaKeyUtils.writeEncoded(key, function, path);
    }

    /**
     * Writes the encoded form of specified private key to specified path using {@link PKCS8EncodedKeySpec}.
     *
     * @param key  the private key to write.
     * @param path the path to which the encoded key is written.
     * @throws IOException if an I/O error occurs.
     */
    public static void writeEncoded(final PrivateKey key, final Path path)
            throws IOException {
        writeEncoded(key, PKCS8, path);
    }

    /**
     * Reads an encoded private key from specified path.
     *
     * @param path     the path from which the encoded key is read.
     * @param function a function which creates an encoded key spec from bytes read.
     * @param factory  a key factory for generating the private key.
     * @return the private key read.
     * @throws IOException if an I/O error occurs.
     */
    public static PrivateKey readEncoded(
            final Path path, final Function<? super byte[], ? extends EncodedKeySpec> function,
            final KeyFactory factory)
            throws IOException {
        return JinahyaKeyUtils.readEncoded(
                path,
                function,
                ks -> {
                    try {
                        return factory.generatePrivate(ks);
                    } catch (final InvalidKeySpecException ikse) {
                        throw new RuntimeException(
                                "failed to generate private key with " + ks,
                                ikse
                        );
                    }
                }
        );
    }

    /**
     * Reads a {@link PKCS8EncodedKeySpec}-encoded private key from specified path.
     *
     * @param path    the path from which the encoded key is read.
     * @param factory a key factory for generating the private key.
     * @return the private key read.
     * @throws IOException if an I/O error occurs.
     */
    public static PrivateKey readEncoded(final Path path, final KeyFactory factory)
            throws IOException {
        return readEncoded(path, PKCS8, factory);
    }

    @_ExcludeFromCoverage_PrivateConstructor_Obviously
    private JinahyaPrivateKeyUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
