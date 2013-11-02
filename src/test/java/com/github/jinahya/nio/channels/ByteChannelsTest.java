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


import com.github.jinahya.io.BlackOutputStream;
import com.github.jinahya.io.IoTests;
import com.github.jinahya.io.WhiteInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class ByteChannelsTest {


    /**
     * logger.
     */
    private static final Logger LOGGER
        = LoggerFactory.getLogger(ByteChannelsTest.class);


    @Test(enabled = true, invocationCount = 32)
    public void copy_file2file() throws IOException {

        final long sourceLength = IoTests.newTempFileLength();
        final File source = IoTests.newTempFile(sourceLength);

        final long targetLength = IoTests.newTempFileLength();
        final File target = IoTests.newTempFile(targetLength);

        final long count = ByteChannels.copy(source, target, -1L);

        Assert.assertEquals(count, sourceLength);
        Assert.assertEquals(target.length(), source.length());
    }


    @Test(enabled = true, invocationCount = 32)
    public void copy_SmallerFile2BiggerChannel() throws IOException {

        final long length = IoTests.newTempFileLength();
        final File file = IoTests.newTempFile(length);

        final long limit = length + 1;
        final WritableByteChannel channel
            = new BlackOutputStream(limit).newChannel();

        final long count = ByteChannels.copy(file, channel, -1L);

        Assert.assertEquals(count, length);
    }


    @Test(enabled = true, invocationCount = 32)
    public void copy_BiggerFile2SmallerChannel() throws IOException {

//        final long limit = ThreadLocalRandom.current().nextInt(65535);
//        LOGGER.trace("limit: {}", limit);
//        final ReadableByteChannel input
//            = new WhiteInputStream(limit).newChannel();
//
//        final File output = newTempFile(limit + 1);
//
//        final long count = ByteChannels.copy(
//            input, output, ByteBuffer.allocate(1024), -1L);
//
//        Assert.assertEquals(count, limit);
//        Assert.assertEquals(output.length(), limit);
    }


    @Test(enabled = true, invocationCount = 32)
    public void copy_SmallerChannel2BiggerFile() throws IOException {

        final long limit = ThreadLocalRandom.current().nextInt(65535);
        LOGGER.trace("limit: {}", limit);
        final ReadableByteChannel input
            = new WhiteInputStream(limit).newChannel();

        final File output = IoTests.newTempFile(limit + 1);

        final long count = ByteChannels.copy(input, output, -1L);

        Assert.assertEquals(count, limit);
        Assert.assertEquals(output.length(), limit);
    }


    @Test(enabled = true, invocationCount = 32)
    public void copy_BiggerChannel2SmallerFile() throws IOException {

        final long limit = ThreadLocalRandom.current().nextInt(65535) + 1;
        assert limit > 0L;
        LOGGER.trace("limit: {}", limit);
        final ReadableByteChannel input
            = new WhiteInputStream(limit).newChannel();

        final long length = ThreadLocalRandom.current().nextLong(0, limit);
        LOGGER.trace("size: {}", length);
        assert length < limit;
        final File output = IoTests.newTempFile(length);

        final long count = ByteChannels.copy(input, output, -1L);

        Assert.assertEquals(count, length);
    }


}

