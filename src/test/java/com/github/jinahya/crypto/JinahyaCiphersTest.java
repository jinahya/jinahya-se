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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.jinahya.crypto.JinahyaCiphers.TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class JinahyaCiphersTest {

    private static byte[] iv(final int keysize) {
        final var bytes = (int) Math.ceil(((double) keysize / Byte.SIZE) / Byte.SIZE) * Byte.SIZE;
        final var iv = new byte[bytes];
        ThreadLocalRandom.current().nextBytes(iv);
        return iv;
    }

    @DisplayName("TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED")
    @Nested
    class TransformationsRequiredToBeSupportedTest {

        @Test
        void Transformation_DoesNotThrow_CipherGetInstance() {
            for (final String transformation : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.keySet()) {
                assertThatCode(() -> {
                    Cipher.getInstance(transformation);
                }).doesNotThrowAnyException();
            }
        }

        @DisplayName("AES/CBC/")
        @Test
        void aes_cbc() throws Exception {
            final var generator = KeyGenerator.getInstance("AES");
            for (final var entry : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.entrySet()) {
                final var transformation = entry.getKey();
                if (!transformation.startsWith("AES/CBC")) {
                    continue;
                }
                final var cipher = Cipher.getInstance(transformation);
                for (final var keysize : entry.getValue()) {
                    generator.init(keysize);
                    final var key = generator.generateKey();
                    final var iv = new IvParameterSpec(iv(keysize));
                    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                    final var plain = new byte[1024];
                    ThreadLocalRandom.current().nextBytes(plain);
                    final var encrypted = cipher.doFinal(plain);
                    cipher.init(Cipher.DECRYPT_MODE, key, iv);
                    final var decrypted = cipher.doFinal(encrypted);
                    assertThat(decrypted).isEqualTo(plain);
                }
            }
        }

        @DisplayName("AES/ECB/")
        @Test
        void aes_ecb() throws Exception {
            final var generator = KeyGenerator.getInstance("AES");
            for (final var entry : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.entrySet()) {
                final var transformation = entry.getKey();
                if (!transformation.startsWith("AES/ECB")) {
                    continue;
                }
                final var cipher = Cipher.getInstance(transformation);
                for (final var keysize : entry.getValue()) {
                    generator.init(keysize);
                    final var key = generator.generateKey();
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    final var plain = new byte[1024];
                    ThreadLocalRandom.current().nextBytes(plain);
                    final var encrypted = cipher.doFinal(plain);
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    final var decrypted = cipher.doFinal(encrypted);
                    assertThat(decrypted).isEqualTo(plain);
                }
            }
        }

        @DisplayName("DES/CBC/")
        @Test
        void des_cbc() throws Exception {
            final var generator = KeyGenerator.getInstance("DES");
            for (final var entry : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.entrySet()) {
                final var transformation = entry.getKey();
                if (!transformation.startsWith("DES/CBC")) {
                    continue;
                }
                final var cipher = Cipher.getInstance(transformation);
                for (final var keysize : entry.getValue()) {
                    generator.init(keysize);
                    final var key = generator.generateKey();
                    final var iv = new IvParameterSpec(iv(keysize));
                    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                    final var plain = new byte[1024];
                    ThreadLocalRandom.current().nextBytes(plain);
                    final var encrypted = cipher.doFinal(plain);
                    cipher.init(Cipher.DECRYPT_MODE, key, iv);
                    final var decrypted = cipher.doFinal(encrypted);
                    assertThat(decrypted).isEqualTo(plain);
                }
            }
        }

        @DisplayName("DES/ECB/")
        @Test
        void des_ecb() throws Exception {
            final var generator = KeyGenerator.getInstance("DES");
            for (final var entry : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.entrySet()) {
                final var transformation = entry.getKey();
                if (!transformation.startsWith("DES/ECB")) {
                    continue;
                }
                final var cipher = Cipher.getInstance(transformation);
                for (final var keysize : entry.getValue()) {
                    generator.init(keysize);
                    final var key = generator.generateKey();
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    final var plain = new byte[1024];
                    ThreadLocalRandom.current().nextBytes(plain);
                    final var encrypted = cipher.doFinal(plain);
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    final var decrypted = cipher.doFinal(encrypted);
                    assertThat(decrypted).isEqualTo(plain);
                }
            }
        }
    }

    @Test
    void getAlgorithm() {
        for (final var transformation : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.keySet()) {
            assertThat(JinahyaCiphers.getAlgorithm(transformation)).isNotBlank();
        }
    }

    @Test
    void getMode() {
        for (final var transformation : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.keySet()) {
            assertThat(JinahyaCiphers.getMode(transformation)).satisfiesAnyOf(
                    m -> assertThat(m).isEmpty(),
                    m -> assertThat(m).hasValueSatisfying(v -> {
                        assertThat(v).isNotBlank();
                    })
            );
        }
    }

    @Test
    void getPadding() {
        for (final var transformation : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.keySet()) {
            assertThat(JinahyaCiphers.getPadding(transformation)).satisfiesAnyOf(
                    m -> assertThat(m).isEmpty(),
                    m -> assertThat(m).hasValueSatisfying(v -> {
                        assertThat(v).isNotBlank();
                    })
            );
        }
    }

    @Test
    void parseTransformation() {
        for (final var transformation : TRANSFORMATIONS_AND_KEYSIZES_REQUIRED_TO_BE_SUPPORTED.keySet()) {
            JinahyaCiphers.parseTransformation(
                    transformation,
                    a -> m -> p -> {
                        assertThat(a).isNotBlank();
                        assertThat(m).satisfiesAnyOf(
                                m2 -> assertThat(m2).isNull(),
                                m2 -> assertThat(m2).isNotBlank()
                        );
                        assertThat(p).satisfiesAnyOf(
                                p2 -> assertThat(p2).isNull(),
                                p2 -> assertThat(p2).isNotBlank()
                        );
                        return null;
                    }
            );
        }
    }
}
