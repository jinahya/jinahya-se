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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class CiphersTest {

    private static final Logger logger
            = LoggerFactory.getLogger(CiphersTest.class);

    @Test
    public static void SUPPORTED_TRANSFORMATIONS()
            throws NoSuchAlgorithmException, NoSuchPaddingException {

        for (final String transformation
             : JinahyaCiphers.SUPPORTED_TRANSFORMATIONS.keySet()) {
            final Cipher cipher = Cipher.getInstance(transformation);
        }
    }

    private static Key newKey(final String algorithm, final int keySize)
            throws NoSuchAlgorithmException {

        final KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
        keygen.init(keySize);

        return keygen.generateKey();
    }

    private static AlgorithmParameterSpec newIv(final int blockSize) {

        if (blockSize == 0) {
            return null;
        }

        final byte[] iv = new byte[blockSize];
        ThreadLocalRandom.current().nextBytes(iv);

        return new IvParameterSpec(iv);
    }

    private static AlgorithmParameterSpec newIv(final Cipher cipher) {

        return newIv(cipher.getBlockSize());
    }

    private static KeyPair newKeyPair(final String algorithm, final int keySize)
            throws NoSuchAlgorithmException {

        final KeyPairGenerator keygen = KeyPairGenerator.getInstance(algorithm);
        keygen.initialize(keySize);

        return keygen.generateKeyPair();
    }

    private static void symmetric(final String transformation,
                                  final int keySize, final boolean requiresIv,
                                  final boolean noPadding)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
                   InvalidKeyException, InvalidAlgorithmParameterException,
                   IOException, IllegalBlockSizeException, BadPaddingException {

        logger.debug("symmetric({}, {}, {}, {})",
                     transformation, keySize, requiresIv, noPadding);

        final Random random = ThreadLocalRandom.current();

        final String algorithm
                = transformation.substring(0, transformation.indexOf('/'));
        final Key key = newKey(algorithm, keySize);
        final Cipher cipher = Cipher.getInstance(transformation);
        final AlgorithmParameterSpec iv = requiresIv ? newIv(cipher) : null;

        final byte[] expected
                = noPadding
                  ? new byte[cipher.getBlockSize() * random.nextInt(512)]
                  : new byte[random.nextInt(65536)];
        random.nextBytes(expected);

        {
            int length = 1;
            final int blockSize = cipher.getBlockSize();
            if (blockSize != 0 && length < blockSize) {
                length = blockSize;
            }

            final byte[] encrypted;
            final byte[] actual;
            {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JinahyaCiphers.update(
                        cipher, new ByteArrayInputStream(expected), baos, length,
                        -1L, true);
                encrypted = baos.toByteArray();
            }
            {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JinahyaCiphers.update(
                        cipher, new ByteArrayInputStream(encrypted), baos, length,
                        -1L, true);
                actual = baos.toByteArray();
            }
            Assert.assertEquals(actual, expected);
        }

        {
            //int capacity = current().nextInt(1, 1024);
            int capacity = 1;
            final int blockSize = cipher.getBlockSize();
            if (blockSize != 0 && capacity < blockSize) {
                capacity = blockSize;
            }

            final byte[] encrypted;
            final byte[] actual;
            {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                final ReadableByteChannel input
                        = Channels.newChannel(new ByteArrayInputStream(expected));
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final WritableByteChannel output = Channels.newChannel(baos);
                JinahyaCiphers.update(
                        cipher, input, output, capacity, -1L, true);
                baos.flush();
                encrypted = baos.toByteArray();
            }
            {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                final ReadableByteChannel input
                        = Channels.newChannel(new ByteArrayInputStream(encrypted));
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final WritableByteChannel output = Channels.newChannel(baos);
                JinahyaCiphers.update(
                        cipher, input, output, capacity, -1L, true);
                actual = baos.toByteArray();
                Assert.assertEquals(actual, expected);
            }
        }
    }

    @Test(invocationCount = 1)
    public void test() throws Exception {

        for (Entry<String, List<Integer>> entry
             : JinahyaCiphers.SUPPORTED_TRANSFORMATIONS.entrySet()) {

            final String transformation = entry.getKey();
            final String[] split = transformation.split("/");
            final String algorithm = split[0];
            final String mode = split[1];
            final String padding = split[2];
            final List<Integer> keysizes = entry.getValue();

            boolean requiresIv = true;
            if ("ECB".equals(mode)) {
                requiresIv = false;
            }

            boolean noPadding = false;
            if ("NoPadding".equals(padding)) {
                noPadding = true;
            }

            for (int keySize : keysizes) {
                if (algorithm.equals("RSA")) {
                    continue;
                }
                symmetric(transformation, keySize, requiresIv, noPadding);
            }
        }
    }

}
