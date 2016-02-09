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
import java.io.RandomAccessFile;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IoTests {


    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IoTests.class);


    public static long newTempFileLength() {

        return ThreadLocalRandom.current().nextLong(65536);
    }


    public static File newTempFile(final long length) throws IOException {

        LOGGER.trace("newTempFile({})", length);

        final File file = File.createTempFile(IoTests.class.getName(), null);
        file.deleteOnExit();

        final RandomAccessFile raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(length);
        } finally {
            raf.close();
        }

        LOGGER.debug("file.length: {}", file.length());

        Assert.assertEquals(file.length(), length);

        return file;
    }


    /**
     * private constructor.
     */
    private IoTests() {

        super();
    }

}

