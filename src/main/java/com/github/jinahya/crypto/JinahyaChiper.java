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
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaChiper {


    @XmlEnum(Integer.class)
    public static enum Opmode implements IntFieldEnum<Opmode> {

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
        UNWRAP_MODE(Cipher.UNWRAP_MODE); // 4// 4// 4// 4


        private Opmode(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    @XmlEnum(Integer.class)
    public static enum KeyType implements IntFieldEnum<KeyType> {

        /**
         * A constant for {@link Cipher#PUBLIC_KEY}.
         */
        @XmlEnumValue("1")
        PUBLIC_KEY(Cipher.PUBLIC_KEY), // 1

        /**
         * A constant for {@link Cipher#PRIVATE_KEY}.
         */
        @XmlEnumValue("2")
        PRIVATE_KEY(Cipher.PRIVATE_KEY), // 2

        /**
         * A constant for {@link Cipher#SECRET_KEY}.
         */
        @XmlEnumValue("3")
        SECRET_KEY(Cipher.SECRET_KEY); // 3// 3


        private KeyType(final int fieldValue) {

            this.fieldValue = fieldValue;
        }


        @Override
        public int fieldValueAsInt() {

            return fieldValue;
        }


        private final int fieldValue;

    }


    private JinahyaChiper() {

        super();
    }


}

