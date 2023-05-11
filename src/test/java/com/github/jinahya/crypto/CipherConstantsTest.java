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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Slf4j
class CipherConstantsTest {

    @Test
    void TRANSFORMATIONS_REQUIRED_TO_BE_SUPPORTED() throws NoSuchAlgorithmException, NoSuchPaddingException {
        for (final String transformation : CipherConstants.TRANSFORMATIONS_REQUIRED_TO_BE_SUPPORTED.keySet()) {
            final Cipher cipher = Cipher.getInstance(transformation);
        }
    }
}
