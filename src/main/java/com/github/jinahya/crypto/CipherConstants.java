/*
 * Copyright 2012 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.crypto;

import javax.crypto.Cipher;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Constants for {@link Cipher}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class CipherConstants {

    /**
     * An unmodifiable map of transformations and lists of available key sizes that every implementation of the Java
     * platform is required to support.
     *
     * @see Cipher
     */
    public static final Map<String, List<Integer>> TRANSFORMATIONS_REQUIRED_TO_BE_SUPPORTED;

    static {
        final Map<String, List<Integer>> m = new HashMap<>();
        m.put("AES/CBC/NoPadding", Collections.singletonList(128));
        m.put("AES/CBC/PKCS5Padding", Collections.singletonList(128));
        m.put("AES/ECB/NoPadding", Collections.singletonList(128));
        m.put("AES/ECB/PKCS5Padding", Collections.singletonList(128));
        m.put("DES/CBC/NoPadding", Collections.singletonList(56));
        m.put("DES/CBC/PKCS5Padding", Collections.singletonList(56));
        m.put("DES/ECB/NoPadding", Collections.singletonList(56));
        m.put("DES/ECB/PKCS5Padding", Collections.singletonList(56));
        m.put("DESede/CBC/NoPadding", Collections.singletonList(168));
        m.put("DESede/CBC/PKCS5Padding", Collections.singletonList(168));
        m.put("DESede/ECB/NoPadding", Collections.singletonList(168));
        m.put("DESede/ECB/PKCS5Padding", Collections.singletonList(168));
        m.put("RSA/ECB/PKCS1Padding", Arrays.asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", Arrays.asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", Arrays.asList(1024, 2048));
        TRANSFORMATIONS_REQUIRED_TO_BE_SUPPORTED = Collections.unmodifiableMap(m);
    }
}
