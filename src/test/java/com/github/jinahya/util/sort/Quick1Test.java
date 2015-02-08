/*
 * Copyright 2014 Jin Kwon.
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


package com.github.jinahya.util.sort;


import static java.util.concurrent.ThreadLocalRandom.current;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon
 */
public class Quick1Test {


    private static final Logger logger
        = LoggerFactory.getLogger(Quick1Test.class);


    @Test
    public void sort() {

        final byte[] a = new byte[current().nextInt(1024)];
        current().nextBytes(a);

        new Quick1<?>().sort(a);

        for (int i = 1; i < a.length; i++) {
            assertTrue(a[i - 1] <= a[i]);
        }
    }


}

