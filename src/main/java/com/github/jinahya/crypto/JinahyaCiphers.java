/*
 * Copyright 2012 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.crypto;

import javax.crypto.Cipher;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Constants for {@link Cipher}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaCiphers {

    /**
     * An unmodifiable map of transformations and lists of available key sizes that every implementation of the Java
     * platform is required to support.
     *
     * @see Cipher
     */
    public static final Map<String, List<Integer>> TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED;

    static {
        TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED = Map.ofEntries(
                Map.entry("AES/CBC/NoPadding", Collections.singletonList(128)),
                Map.entry("AES/CBC/PKCS5Padding", Collections.singletonList(128)),
                Map.entry("AES/ECB/NoPadding", Collections.singletonList(128)),
                Map.entry("AES/ECB/PKCS5Padding", Collections.singletonList(128)),
                Map.entry("DES/CBC/NoPadding", Collections.singletonList(56)),
                Map.entry("DES/CBC/PKCS5Padding", Collections.singletonList(56)),
                Map.entry("DES/ECB/NoPadding", Collections.singletonList(56)),
                Map.entry("DES/ECB/PKCS5Padding", Collections.singletonList(56)),
                Map.entry("DESede/CBC/NoPadding", Collections.singletonList(168)),
                Map.entry("DESede/CBC/PKCS5Padding", Collections.singletonList(168)),
                Map.entry("DESede/ECB/NoPadding", Collections.singletonList(168)),
                Map.entry("DESede/ECB/PKCS5Padding", Collections.singletonList(168)),
                Map.entry("RSA/ECB/PKCS1Padding", Arrays.asList(1024, 2048)),
                Map.entry("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", Arrays.asList(1024, 2048)),
                Map.entry("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", Arrays.asList(1024, 2048))
        );
    }

    private static String getAlgorithm(final String[] transformationTokens) {
        if (transformationTokens.length == 0) {
            throw new IllegalArgumentException("wrong transformationTokens: " + Arrays.toString(transformationTokens));
        }
        return transformationTokens[0];
    }

    private static String getMode(final String[] transformationTokens) {
        if (transformationTokens.length < 2) {
            return null;
        }
        return transformationTokens[1];
    }

    private static String getPadding(final String[] transformationTokens) {
        if (transformationTokens.length < 3) {
            return null;
        }
        return transformationTokens[2];
    }

    public static String getAlgorithm(final String transformation) {
        Objects.requireNonNull(transformation, "transformation is null");
        return getAlgorithm(transformation.split("/"));
    }

    public static Optional<String> getMode(final String transformation) {
        Objects.requireNonNull(transformation, "transformation is null");
        return Optional.ofNullable(getMode(transformation.split("/")));
    }

    public static Optional<String> getPadding(final String transformation) {
        Objects.requireNonNull(transformation, "transformation is null");
        return Optional.ofNullable(getPadding(transformation.split("/")));
    }

    public static <R> R parseTransformation(
            final String transformation,
            final Function<? super String,
                    ? extends Function<? super String,
                            ? extends Function<? super String,
                                    ? extends R>>> function) {
        Objects.requireNonNull(transformation, "transformation is null");
        Objects.requireNonNull(function, "function is null");
        final var tokens = transformation.split("/");
        return function.apply(getAlgorithm(tokens))
                .apply(getMode(tokens))
                .apply(getPadding(tokens));
    }

    private JinahyaCiphers() {
        throw new AssertionError("instantiation is not allowed");
    }
}
