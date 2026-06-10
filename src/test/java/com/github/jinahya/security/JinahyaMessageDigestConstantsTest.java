package com.github.jinahya.security;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
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
 * #L%
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class JinahyaMessageDigestConstantsTest {

    private static List<String> algorithmsRequiredToBeSupported() {
        return JinahyaMessageDigestConstants.ALGORITHMS_REQUIRED_TO_BE_SUPPORTED;
    }

    private static List<String> algorithms() {
        return JinahyaMessageDigestConstants.ALGORITHMS;
    }

    @MethodSource({"algorithmsRequiredToBeSupported"})
    @ParameterizedTest
    void _ShouldBeSupported_(final String algorithm) {
        assertThatCode(() -> MessageDigest.getInstance(algorithm)).doesNotThrowAnyException();
    }

    @MethodSource({"algorithms"})
    @ParameterizedTest
    void _MayBeSupported_(final String algorithm) {
        try {
            MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException nsae) {
            log.info("seems not supported: {}", algorithm, nsae);
        }
    }
}
