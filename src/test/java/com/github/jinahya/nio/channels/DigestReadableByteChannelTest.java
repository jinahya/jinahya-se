/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.nio.channels;

import com.github.jinahya.security.JinahyaMessageDigests;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@Disabled
public class DigestReadableByteChannelTest {

    @Test
    public void test() throws NoSuchAlgorithmException, IOException {

        final Random random = ThreadLocalRandom.current();

        final byte[] data = new byte[random.nextInt(65536)];
        random.nextBytes(data);

        for (String algorithm : JinahyaMessageDigests.ALGORITHMS_REQUIRED_TO_BE_SUPPORTED) {
            System.out.println("algorithm: " + algorithm);

            final MessageDigest digest1 = MessageDigest.getInstance(algorithm);

            final byte[] expected = digest1.digest(data);
            for (final byte b : expected) {
                System.out.printf("%1$02X", (b & 0xFF));
            }
            System.out.println();

            final MessageDigest digest2 = MessageDigest.getInstance(algorithm);
            final ReadableDigestChannel channel
                    = new ReadableDigestChannel(Channels.newChannel(new ByteArrayInputStream(data)));
            channel.setDigest(digest2);
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            for (; channel.read(buffer) != -1; buffer.clear()) {
                buffer.flip();
            }

            final byte[] actual = digest2.digest();
            for (final byte b : actual) {
                System.out.printf("%1$02X", (b & 0xFF));
            }
            System.out.println();

            assertEquals(actual, expected);
        }
    }
}
