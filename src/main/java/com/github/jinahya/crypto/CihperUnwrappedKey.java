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
public enum CihperUnwrappedKey implements IntegerFieldEnum<CihperUnwrappedKey> {


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
    SECRET_KEY(Cipher.SECRET_KEY); // 3


    private CihperUnwrappedKey(final int fieldValue) {

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

