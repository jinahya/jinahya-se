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
package com.github.jinahya.security;

import static java.lang.invoke.MethodHandles.lookup;
import java.net.URL;
import java.util.Optional;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class CodeSourcesTest {

    @Test
    public static void getLocation() {

        final Logger logger = getLogger(lookup().lookupClass());

        final Optional<URL> location
                = CodeSources.getLocation(CodeSourcesTest.class);

        assertTrue(location.isPresent());
        logger.debug("location: {}", location.get());
    }

}
