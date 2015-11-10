/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class FunnelOutputStreamTest {


    @Test(invocationCount = 128)
    public void write_() throws IOException {

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final byte[] bytes = new byte[random.nextInt(65536)];
        random.nextBytes(bytes);

        final ByteArrayOutputStream baos =
            new ByteArrayOutputStream(bytes.length);

        final InputStream bais = new ByteArrayInputStream(bytes);
        try (final OutputStream fos = new FunnelOutputStream(baos)) {
            final byte[] buf = new byte[1024];
            for (int read; (read = bais.read(buf)) != -1;) {
                fos.write(buf, 0, read);
            }
        }

        Assert.assertEquals(baos.toByteArray(), bytes);
    }

}

