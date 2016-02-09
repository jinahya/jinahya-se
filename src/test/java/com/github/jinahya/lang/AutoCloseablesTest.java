/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


package com.github.jinahya.lang;


import static java.lang.invoke.MethodHandles.lookup;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class AutoCloseablesTest {


    private static final Logger logger = getLogger(lookup().lookupClass());


    private static class NotAutoCloseable {


        public void notClose() {

            logger.debug("I'm not the close()");
        }

    }


    @Test
    public static void of() throws Exception {

        final AutoCloseable autoCloseable = AutoCloseables.of(
            AutoCloseablesTest.class.getClassLoader(), new NotAutoCloseable(),
            o -> o.notClose());

        try (AutoCloseable resource = autoCloseable) {
        }
    }

}

