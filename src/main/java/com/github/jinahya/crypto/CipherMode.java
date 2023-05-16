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
package com.github.jinahya.crypto;

import com.github.jinahya.lang.FieldEnum;

import javax.crypto.Cipher;

/**
 * Constants for modes of ciphers defined in {@link Cipher}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public enum CipherMode implements FieldEnum.OfInt<CipherMode> {

    /**
     * A constant for {@link Cipher#ENCRYPT_MODE}.
     */
    ENCRYPT_MODE(Cipher.ENCRYPT_MODE), // 1

    /**
     * A constant for {@link Cipher#DECRYPT_MODE}.
     */
    DECRYPT_MODE(Cipher.DECRYPT_MODE), // 2

    /**
     * A constant for {@link Cipher#WRAP_MODE}.
     */
    WRAP_MODE(Cipher.WRAP_MODE), // 3

    /**
     * A constant for {@link Cipher#UNWRAP_MODE}.
     */
    UNWRAP_MODE(Cipher.UNWRAP_MODE); // 4

//    public static int[] fieldValues() {
//        return IntFieldEnum.fieldValues(CipherMode.class);
//    }
//
//    public static CipherMode valueOfFieldValue(final int fieldValue) {
//        return IntFieldEnum.valueOfFieldValue(CipherMode.class, fieldValue);
//    }

    CipherMode(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
