/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


import com.github.jinahya.nio.channels.DigestWritableByteChannel;
import com.github.jinahya.io.BlackOutputStream;
import com.github.jinahya.security.MessageDigests;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class DigestWritableByteChannelTest {


    @Test(invocationCount = 128)
    public void test() throws NoSuchAlgorithmException, IOException {

        final Random random = ThreadLocalRandom.current();

        final byte[] data = new byte[random.nextInt(65536)];
        random.nextBytes(data);

        for (String algorithm : MessageDigests.SUPPORTED_ALGORITHMS) {
            System.out.println("algorithm: " + algorithm);

            final MessageDigest digest1 = MessageDigest.getInstance(algorithm);

            final byte[] expected = digest1.digest(data);
            for (int b : expected) {
                System.out.printf("%1$02X", (b & 0xFF));
            }
            System.out.println();

            final MessageDigest digest2 = MessageDigest.getInstance(algorithm);
            final DigestWritableByteChannel channel =
                new DigestWritableByteChannel(
                Channels.newChannel(new BlackOutputStream(-1L)), digest2);
            final ByteBuffer buffer = ByteBuffer.wrap(data);
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }

            final byte[] actual = digest2.digest();
            for (int b : actual) {
                System.out.printf("%1$02X", (b & 0xFF));
            }
            System.out.println();


            Assert.assertEquals(actual, expected);
        }
    }


}
