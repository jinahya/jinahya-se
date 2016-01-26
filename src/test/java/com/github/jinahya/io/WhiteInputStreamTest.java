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


package com.github.jinahya.io;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class WhiteInputStreamTest {


    @Test
    public void testRead() throws IOException {

        try (final InputStream in = new LimitedWhiteInputStream(-1L)) {
            for (int read, i = 0; i < 1048576; i++) {
                read = in.read();
                Assert.assertTrue(read >= 0x00 && read < 0x0100);
            }
        }
    }


    @Test
    public void testReadUnderLimit() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long limit = random.nextInt(1048576) + 1;

        try (final InputStream in = new LimitedWhiteInputStream(limit)) {
            final int count = random.nextInt((int) limit);
            for (int i = 0; i < count; i++) {
                in.read();
            }
        }
    }


    @Test
    public void testWriteOverLimit() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long limit = random.nextInt(1048576);

        try (final InputStream in = new LimitedWhiteInputStream(limit)) {
            in.skip(limit);
            Assert.assertEquals(in.read(), -1);
            Assert.assertEquals(in.read(), -1);
        }
    }

}

