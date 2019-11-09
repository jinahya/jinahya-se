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
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.List;
import java.util.Map.Entry;

import static com.github.jinahya.crypto.JinahyaCiphers.SUPPORTED_TRANSFORMATIONS;
import static com.github.jinahya.crypto.JinahyaCiphers.update;
import static java.nio.ByteBuffer.allocate;
import static java.nio.channels.Channels.newChannel;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@Slf4j
public class CiphersTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public static void SUPPORTED_TRANSFORMATIONS() throws NoSuchAlgorithmException, NoSuchPaddingException {
        for (final String transformation : SUPPORTED_TRANSFORMATIONS.keySet()) {
            final Cipher cipher = Cipher.getInstance(transformation);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Key newKey(final String algorithm, final int keySize) throws NoSuchAlgorithmException {
        final KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
        keygen.init(keySize);
        return keygen.generateKey();
    }

    private static AlgorithmParameterSpec newIv(final int blockSize) {
        if (blockSize == 0) {
            return null;
        }
        final byte[] iv = new byte[blockSize];
        current().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static AlgorithmParameterSpec newIv(final Cipher cipher) {
        return newIv(cipher.getBlockSize());
    }

    private static KeyPair newKeyPair(final String algorithm, final int keySize) throws NoSuchAlgorithmException {
        final KeyPairGenerator keygen = KeyPairGenerator.getInstance(algorithm);
        keygen.initialize(keySize);
        return keygen.generateKeyPair();
    }

    private static void symmetric(final String transformation, final int keySize, final boolean requiresIv,
                                  final boolean noPadding)
            throws Exception {
        final String algorithm = transformation.substring(0, transformation.indexOf('/'));
        final Key key = newKey(algorithm, keySize);
        final Cipher cipher = Cipher.getInstance(transformation);
        final int blockSize = cipher.getBlockSize();
        final AlgorithmParameterSpec iv = requiresIv ? newIv(cipher) : null;
        final byte[] plain = new byte[noPadding ? blockSize * current().nextInt(1, 16) : current().nextInt(1024)];
        current().nextBytes(plain);
        {
            final byte[] encrypted;
            final byte[] decrypted;
            int outputSize;
            {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                final ByteArrayInputStream input = new ByteArrayInputStream(plain);
                final ByteArrayOutputStream output = new ByteArrayOutputStream();
                final byte[] inbuf = new byte[current().nextInt(1, 16)];
                outputSize = cipher.getOutputSize(inbuf.length);
                final long count = update(cipher, input, output, inbuf, Long.MAX_VALUE, true);
                assertEquals(plain.length, count);
                encrypted = output.toByteArray();
            }
            {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                final ByteArrayInputStream input = new ByteArrayInputStream(encrypted);
                final ByteArrayOutputStream output = new ByteArrayOutputStream();
                final byte[] outbuf = new byte[outputSize];
                final long count = update(cipher, input, output, outbuf, Long.MAX_VALUE, true);
                assertEquals(encrypted.length, count);
                decrypted = output.toByteArray();
            }
            assertArrayEquals(plain, decrypted);
        }
        {
            final byte[] encrypted;
            final byte[] decrypted;
            int outputSize;
            {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                final ReadableByteChannel readable = newChannel(new ByteArrayInputStream(plain));
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final WritableByteChannel writable = newChannel(baos);
                final ByteBuffer inbuf = allocate(current().nextInt(1, 16));
                outputSize = cipher.getOutputSize(inbuf.capacity());
                final long count = update(cipher, readable, writable, inbuf, Long.MAX_VALUE, true);
                assertEquals(plain.length, count);
                baos.flush();
                encrypted = baos.toByteArray();
            }
            {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                final ReadableByteChannel readable = newChannel(new ByteArrayInputStream(encrypted));
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final WritableByteChannel writable = newChannel(baos);
                final ByteBuffer inbuf = allocate(outputSize);
                final long count = update(cipher, readable, writable, inbuf, Long.MAX_VALUE, true);
                assertEquals(encrypted.length, count);
                decrypted = baos.toByteArray();
            }
            assertArrayEquals(plain, decrypted);
        }
    }

    @Test
    public void symmetric() throws Exception {
        for (final Entry<String, List<Integer>> entry : SUPPORTED_TRANSFORMATIONS.entrySet()) {
            final String transformation = entry.getKey();
            final String[] split = transformation.split("/");
            final String algorithm = split[0];
            final String mode = split[1];
            final String padding = split[2];
            final List<Integer> keySizes = entry.getValue();
            boolean requiresIv = true;
            if ("ECB".equals(mode)) {
                requiresIv = false;
            }
            boolean noPadding = false;
            if ("NoPadding".equals(padding)) {
                noPadding = true;
            }
            for (int keySize : keySizes) {
                if (algorithm.equals("RSA")) {
                    continue;
                }
                symmetric(transformation, keySize, requiresIv, noPadding);
            }
        }
    }
}
