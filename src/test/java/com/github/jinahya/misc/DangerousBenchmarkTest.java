/*
 * Copyright 2014 Jin Kwon <onacit at gmail.com>.
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


package com.github.jinahya.misc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import sun.misc.Unsafe;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class DangerousBenchmarkTest {


    private static final Logger logger
        = LoggerFactory.getLogger(DangerousBenchmarkTest.class);


    @Test(enabled = false)
    public void get_() {

        final int rounds = 10;
        final int invokes = 100000000;

        {
            final Unsafe unsafe = (Unsafe) Dangerous.newUnsafeInstance();
            final long address = unsafe.allocateMemory(Long.SIZE / Byte.SIZE);
            try {
                for (int i = 0; i < rounds; i++) {
                    final long start = System.nanoTime();
                    for (int j = 0; j < invokes; j++) {
                        unsafe.putLong(address, j);
                    }
                    final long finish = System.nanoTime();
                    logger.info("unsafe: {}", (finish - start));
                }
            } finally {
                unsafe.freeMemory(address);
            }
        }

        {
            final Dangerous dangerous = Dangerous.newInstance();
            final long address
                = dangerous.allocateMemory(Long.SIZE / Byte.SIZE);
            try {
                for (int i = 0; i < rounds; i++) {
                    final long start = System.nanoTime();
                    for (int j = 0; j < invokes; j++) {
                        dangerous.putLong(address, j);
                    }
                    final long finish = System.nanoTime();
                    logger.info("dangerous: {}", (finish - start));
                }
            } finally {
                dangerous.freeMemory(address);
            }
        }
    }


}

