/*
 * Copyright 2016 Jin Kwon &lt;onacit_at_gmail.com&gt;.
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

import java.lang.reflect.Field;
import static java.util.concurrent.ThreadLocalRandom.current;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import sun.misc.Unsafe;
import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class DangerousTest {

    private static transient final Logger logger = getLogger(Dangerous.class);

    static int sint;
    static volatile int svint;
    int oint;
    volatile int ovint;

    @Test
    public static void constants() {
        logger.debug("INVALID_FIELD_OFFSET: {}", Unsafe.INVALID_FIELD_OFFSET);
        logger.debug("ARRAY_BOOLEAN_BASE_OFFSET: {}", Unsafe.ARRAY_BOOLEAN_BASE_OFFSET);
        logger.debug("ARRAY_BYTE_BASE_OFFSET: {}", Unsafe.ARRAY_BYTE_BASE_OFFSET);
        logger.debug("ARRAY_SHORT_BASE_OFFSET: {}", Unsafe.ARRAY_SHORT_BASE_OFFSET);
        logger.debug("ARRAY_CHAR_BASE_OFFSET: {}", Unsafe.ARRAY_CHAR_BASE_OFFSET);
        logger.debug("ARRAY_INT_BASE_OFFSET: {}", Unsafe.ARRAY_INT_BASE_OFFSET);
        logger.debug("ARRAY_LONG_BASE_OFFSET: {}", Unsafe.ARRAY_LONG_BASE_OFFSET);
        logger.debug("ARRAY_FLOAT_BASE_OFFSET: {}", Unsafe.ARRAY_FLOAT_BASE_OFFSET);
        logger.debug("ARRAY_DOUBLE_BASE_OFFSET: {}", Unsafe.ARRAY_DOUBLE_BASE_OFFSET);
        logger.debug("ARRAY_OBJECT_BASE_OFFSET: {}", Unsafe.ARRAY_OBJECT_BASE_OFFSET);
        logger.debug("ARRAY_BOOLEAN_INDEX_SCALE: {}", Unsafe.ARRAY_BOOLEAN_INDEX_SCALE);
        logger.debug("ARRAY_BYTE_INDEX_SCALE: {}", Unsafe.ARRAY_BYTE_INDEX_SCALE);
        logger.debug("ARRAY_SHORT_INDEX_SCALE: {}", Unsafe.ARRAY_SHORT_INDEX_SCALE);
        logger.debug("ARRAY_CHAR_INDEX_SCALE: {}", Unsafe.ARRAY_CHAR_INDEX_SCALE);
        logger.debug("ARRAY_INT_INDEX_SCALE: {}", Unsafe.ARRAY_INT_INDEX_SCALE);
        logger.debug("ARRAY_LONG_INDEX_SCALE: {}", Unsafe.ARRAY_LONG_INDEX_SCALE);
        logger.debug("ARRAY_FLOAT_INDEX_SCALE: {}", Unsafe.ARRAY_FLOAT_INDEX_SCALE);
        logger.debug("ARRAY_DOUBLE_INDEX_SCALE: {}", Unsafe.ARRAY_DOUBLE_INDEX_SCALE);
        logger.debug("ARRAY_OBJECT_INDEX_SCALE: {}", Unsafe.ARRAY_OBJECT_INDEX_SCALE);
        logger.debug("ADDRESS_SIZE: {}", Unsafe.ADDRESS_SIZE);
    }

    @Test
    public static void theUnsafe() throws ReflectiveOperationException {
        final Unsafe theUnsafe = Dangerous.theUnsafe();
        assertNotNull(theUnsafe);
    }

    @Test
    public static void newUnsafe() throws ReflectiveOperationException {
        final Unsafe newUnsafe = Dangerous.newUnsafe();
        assertNotNull(newUnsafe);
    }

    @Test
    public static void allocateAndFree() throws ReflectiveOperationException {
        final Unsafe unsafe = Dangerous.theUnsafe();
        final long size = current().nextLong(1L, 1024L);
        final long address = unsafe.allocateMemory(size);
        unsafe.freeMemory(address);
    }

    @Test
    public static void reallocateAndFree() throws ReflectiveOperationException {
        final Unsafe unsafe = Dangerous.theUnsafe();
        long size = current().nextLong(1L, 1024L);
        long allocated = unsafe.allocateMemory(size);
        logger.debug("allocated: {}", allocated);
        size = current().nextLong(1L, 1024L);
        allocated = unsafe.reallocateMemory(allocated, size);
        logger.debug("reallocated: {}", allocated);
        unsafe.freeMemory(allocated);
    }

    @Test
    public static void compareAndSwapInt() throws ReflectiveOperationException {
        final Unsafe unsafe = Dangerous.newUnsafe();
        {
            Object base = null;
            final Field field = DangerousTest.class.getDeclaredField("sint");
            assertFalse(Dangerous.compareAndSwapInt(unsafe, base, field, 1, 0));
            assertEquals(DangerousTest.sint, 0);
            assertTrue(Dangerous.compareAndSwapInt(unsafe, base, field, 0, 1));
            assertEquals(DangerousTest.sint, 1);
            base = unsafe.staticFieldBase(field);
            assertFalse(Dangerous.compareAndSwapInt(unsafe, base, field, 0, 1));
            assertTrue(Dangerous.compareAndSwapInt(unsafe, base, field, 1, 0));
            assertEquals(DangerousTest.sint, 0);
        }
    }
}
