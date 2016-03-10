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

import com.github.jinahya.lang.IntFieldEnum;
import javax.crypto.Cipher;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Constants for types of keys defined in {@link Cipher}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlEnum
public enum CipherKey implements IntFieldEnum<CipherKey> {

    /**
     * A constant for {@link Cipher#PUBLIC_KEY}.
     */
    @XmlEnumValue(value = "1")
    PUBLIC_KEY(Cipher.PUBLIC_KEY), // 1

    /**
     * A constant for {@link Cipher#PRIVATE_KEY}.
     */
    @XmlEnumValue(value = "2")
    PRIVATE_KEY(Cipher.PRIVATE_KEY), // 2

    /**
     * A constant for {@link Cipher#SECRET_KEY}.
     */
    @XmlEnumValue(value = "3")
    SECRET_KEY(Cipher.SECRET_KEY); // 3

    private CipherKey(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int fieldValueAsInt() {
        return fieldValue;
    }

    private final int fieldValue;
}
