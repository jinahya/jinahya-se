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


package com.github.jinahya.crypto;


import com.github.jinahya.lang.IntegerFieldEnum;
import javax.crypto.Cipher;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlEnum(Integer.class)
public enum CipherMode implements IntegerFieldEnum<CipherMode> {


    /**
     * A constant for {@link Cipher#ENCRYPT_MODE}.
     */
    @XmlEnumValue("1")
    ENCRYPT_MODE(Cipher.ENCRYPT_MODE), // 1

    /**
     * A constant for {@link Cipher#DECRYPT_MODE}.
     */
    @XmlEnumValue("2")
    DECRYPT_MODE(Cipher.DECRYPT_MODE), // 2

    /**
     * A constant for {@link Cipher#WRAP_MODE}.
     */
    @XmlEnumValue("3")
    WRAP_MODE(Cipher.WRAP_MODE), // 3

    /**
     * A constant for {@link Cipher#UNWRAP_MODE}.
     */
    @XmlEnumValue("4")
    UNWRAP_MODE(Cipher.UNWRAP_MODE); // 4


    private CipherMode(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer fieldValue() {

        return fieldValue;
    }


    /**
     * field value.
     */
    private final int fieldValue;


}

