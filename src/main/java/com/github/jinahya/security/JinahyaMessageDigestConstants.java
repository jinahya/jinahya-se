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
package com.github.jinahya.security;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

/**
 * Constants related to {@link MessageDigest} class.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see JinahyaMessageDigestUtils
 */
public final class JinahyaMessageDigestConstants {

    /**
     * Algorithms that every implementation of the Java platform is required to support.
     */
    public static final List<String> ALGORITHMS_REQUIRED_TO_BE_SUPPORTED = Arrays.asList("MD5", "SHA-1", "SHA-256");

    /**
     * Creates a new instance.
     */
    private JinahyaMessageDigestConstants() {
        super();
    }
}
