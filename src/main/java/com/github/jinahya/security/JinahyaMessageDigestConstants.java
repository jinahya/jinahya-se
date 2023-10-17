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
import java.util.List;

/**
 * Constants for {@link MessageDigest}s.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see MessageDigest
 * @see <a
 * href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/security/MessageDigest.html">java.security.MessageDigest</a>
 * (JDK 21)
 * @see <a href="https://docs.oracle.com/en/java/javase/21/docs/specs/security/standard-names.html">Java Security
 * Standard Algorithm Names</a> (Java SE 21)
 * @see <a
 * href="https://docs.oracle.com/en/java/javase/21/docs/specs/security/standard-names.html#messagedigest-algorithms">MessageDigest
 * Algorithms</> (Java SE 21)
 */
public final class JinahyaMessageDigestConstants {

    public static final String ALGORITHM_SHA_1 = "SHA-1";

    public static final String ALGORITHM_SHA_256 = "SHA-256";

    /**
     * Algorithms that every implementation of the Java platform is required to support.
     */
    public static final List<String> ALGORITHMS_REQUIRED_TO_BE_SUPPORTED = List.of(
            ALGORITHM_SHA_1,
            ALGORITHM_SHA_256
    );

    /**
     * <blockquote>
     * Algorithm names that can be specified when generating an instance of {@link MessageDigest}.
     * </blockquote>
     *
     * @see <a
     * href="https://docs.oracle.com/en/java/javase/21/docs/specs/security/standard-names.html#messagedigest-algorithms">MessageDigest
     * Algorithms</a> (Java SE 21)
     * @see <a href="https://tools.ietf.org/html/rfc1319">RFC 1319</a>
     * @see <a href="https://tools.ietf.org/html/rfc1321">RFC 1321</a>
     * @see <a href="https://csrc.nist.gov/publications/fips/fips180-4/fips-180-4.pdf">FIPS PUB 180-4</a>
     * @see <a href="https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.202.pdf">FIPS PUB 202</a>
     */
    public static final List<String> ALGORITHMS = List.of(
            // ------------------------------------------------------------------------------------------------ RFC 1319
            "MD2",
            // ------------------------------------------------------------------------------------------------ RFC 1321
            "MD5",
            // ------------------------------------------------------------------------------------------ FIPS PUB 180-4
            ALGORITHM_SHA_1,
            "SHA-224",
            ALGORITHM_SHA_256,
            "SHA-384",
            "SHA-512",
            "SHA-512/224",
            "SHA-512/256",
            "SHA-1",
            "SHA-224",
            "SHA-256",
            "SHA-384",
            "SHA-512",
            "SHA-512/224",
            "SHA-512/256",
            // -------------------------------------------------------------------------------------------- FIPS PUB 202
            "SHA3-224",
            "SHA3-256",
            "SHA3-384",
            "SHA3-512"
    );

    private JinahyaMessageDigestConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
