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


    protected static File newTempFile(final long size) throws IOException {

        LOGGER.debug("newTempFile({})", size);

        final File file = File.createTempFile("prefix", null);
        file.deleteOnExit();

        final RandomAccessFile raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(size);
        } finally {
            raf.close();
        }

        LOGGER.debug("file.length: {}", file.length());

        Assert.assertEquals(file.length(), size);

        return file;
    }


    /**
     *
     * @param empty
     *
     * @return
     *
     * @throws IOException
     * @deprecated
     */
    @Deprecated
    protected static File newTempFile(final boolean empty) throws IOException {

        final File f = File.createTempFile("prefix", null);
        f.deleteOnExit();

        if (!empty) {
            // fill input
            final RandomAccessFile raf = new RandomAccessFile(f, "rwd");
            raf.setLength(ThreadLocalRandom.current().nextInt(65536));
            raf.close();
        }

        return f;
    }


    @Test(enabled = false, invocationCount = 32)
    public void testCopyFromFileToFile() throws IOException {

        final File source = newTempFile(false);
        final File target = newTempFile(true);

        final long count = ByteChannels.copy(
            source, target, ByteBuffer.allocate(1024), -1L);

        Assert.assertEquals(count, source.length());
    }


    @Test(enabled = false, invocationCount = 32)
    public void testCopyFromFileToBlackOutput() throws IOException {

        final File input = newTempFile(false);

        final WritableByteChannel output = new BlackOutputStream().newChannel();

        final long count = ByteChannels.copy(
            input, output, ByteBuffer.allocate(1024), -1L);

        Assert.assertEquals(count, input.length());
    }


    @Test(enabled = false, invocationCount = 1)
    public void copy_FromBiggerChannelToSmallerFile() throws IOException {

        final long limit = ThreadLocalRandom.current().nextInt(65535) + 1;
        assert limit > 0L;
        LOGGER.trace("limit: {}", limit);
        final ReadableByteChannel input
            = new WhiteInputStream(limit).newChannel();

        final long size = ThreadLocalRandom.current().nextLong(0, limit);
        LOGGER.trace("size: {}", size);
        assert size < limit;
        final File output = newTempFile(size);

        final long count = ByteChannels.copy(
            input, output, ByteBuffer.allocate(1024), -1L);

        Assert.assertEquals(count, size);
    }


}

