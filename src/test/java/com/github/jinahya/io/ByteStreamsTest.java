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


package com.github.jinahya.io;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ByteStreamsTest {


    @Test(enabled = false, expectedExceptions = {NullPointerException.class})
    public static void copy_nullInput_NullPointerException()
        throws IOException {

        JinahyaByteStreams.copy((InputStream) null, new BlackOutputStream(-1L),
                                new byte[1], -1L);
    }


    @Test(enabled = false, expectedExceptions = {NullPointerException.class})
    public static void copy_nullOutput_NullPointerException()
        throws IOException {

        JinahyaByteStreams.copy(new WhiteInputStream(-1L), (OutputStream) null,
                                new byte[1], -1L);
    }


    @Test(enabled = false, expectedExceptions = {NullPointerException.class})
    public static void copy_nullBuffer_NullPointerException()
        throws IOException {

        JinahyaByteStreams.copy(new WhiteInputStream(-1L), new BlackOutputStream(-1L),
                                null, -1L);
    }


    @Test(enabled = false,
          expectedExceptions = {IllegalArgumentException.class})
    public static void copy_zeroLengthBuffer_IllegalArgumentException()
        throws IOException {

        JinahyaByteStreams.copy(new WhiteInputStream(-1L), new BlackOutputStream(-1L),
                                new byte[0], -1L);
    }


    @Test(enabled = false, invocationCount = 1)
    public static void testCopyWithPositiveLength() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long length = random.nextInt(1048576);

        final long count = JinahyaByteStreams.copy(
            new WhiteInputStream(-1L), new BlackOutputStream(-1L),
            new byte[8192], length);

        Assert.assertEquals(count, length);
    }


    @Test(enabled = false, invocationCount = 1)
    public static void copy_stream2stream() throws IOException {

        final ThreadLocalRandom random = ThreadLocalRandom.current();

        final long limit = random.nextInt(1048576);

        final InputStream input = new WhiteInputStream(limit);
        final OutputStream output = new BlackOutputStream(-1L);

        final long length
            = random.nextBoolean() ? -1L : random.nextLong(limit);

        final long count
            = JinahyaByteStreams.copy(input, output, new byte[8192], length);

        if (length == -1) {
            Assert.assertEquals(count, limit);
        } else {
            Assert.assertEquals(count, length);
        }
    }


    @Test(enabled = false, invocationCount = 1)
    public static void copy_file2file() throws IOException {

        final ThreadLocalRandom random = ThreadLocalRandom.current();

        final long fileLength1 = IoTests.newTempFileLength();
        final File file1 = IoTests.newTempFile(fileLength1);

        final long fileLength2 = IoTests.newTempFileLength();
        final File file2 = IoTests.newTempFile(fileLength2);

        final long length
            = random.nextBoolean() ? -1L : random.nextLong(fileLength1);

        final long count = JinahyaByteStreams.copy(
            file1, file2, new byte[8192], length);

        if (length == -1L) {
            Assert.assertEquals(count, fileLength1);
            Assert.assertEquals(file2.length(), file1.length());
        } else {
            Assert.assertEquals(count, length);
            Assert.assertEquals(file2.length(), length);
        }
    }

}

