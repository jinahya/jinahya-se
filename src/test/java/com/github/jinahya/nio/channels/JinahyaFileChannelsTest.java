/*
 * Copyright 2016 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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

import org.slf4j.Logger;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.channels.Channels.newChannel;
import static java.nio.channels.FileChannel.open;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JinahyaFileChannelsTest {

    @Test
    public void transferFrom1() throws IOException {
        final Path path = Files.createTempFile(null, null);
        try (RandomAccessFile raw
                     = new RandomAccessFile(path.toFile(), "rwd")) {
            raw.setLength(1024);
        }
        logger.debug("path.size: {}", Files.size(path));
        try (FileChannel dst = open(path, WRITE)) {
            final ReadableByteChannel src
                    = newChannel(new ByteArrayInputStream(new byte[1024]));
            dst.transferFrom(src, 1025, 1024);
            dst.force(true);
        }
        logger.debug("path.size: {}", Files.size(path));
    }

    @Test
    public void transferFrom2() throws IOException {
        final Path path = Files.createTempFile(null, null);
        try (RandomAccessFile raw
                     = new RandomAccessFile(path.toFile(), "rwd")) {
            raw.setLength(1024);
        }
        logger.debug("path.size: {}", Files.size(path));
        try (FileChannel dst = open(path, APPEND)) {
            final ReadableByteChannel src
                    = newChannel(new ByteArrayInputStream(new byte[1024]));
            dst.transferFrom(src, 1025, 1024);
            dst.force(true);
        }
        logger.debug("path.size: {}", Files.size(path));
    }

    @Test
    public void test() throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.position(buffer.limit());
        logger.debug("read: {}", newChannel(new ByteArrayInputStream(new byte[0])).read(buffer));
    }

    private transient final Logger logger = getLogger(getClass());
}
