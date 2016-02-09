/*
 * Copyright 2014 Jin Kwon.
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


package com.github.jinahya.nio.charset;


import com.github.jinahya.lang.ComparableFieldEnum;
import com.github.jinahya.lang.FieldEnums;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;


/**
 * Constants for fields defined in {@link StandardCharsets}.
 *
 * @author Jin Kwon
 */
public enum StandardCharset
    implements ComparableFieldEnum<StandardCharset, Charset> {

    /**
     * Constant for {@link StandardCharsets#ISO_8859_1}.
     */
    ISO_8859_1(StandardCharsets.ISO_8859_1),
    /**
     * Constant for {@link StandardCharsets#US_ASCII}.
     */
    US_ASCII(StandardCharsets.US_ASCII),
    /**
     * Constant for {@link StandardCharsets#UTF_16}.
     */
    UTF_16(StandardCharsets.UTF_16),
    /**
     * Constant for {@link StandardCharsets#UTF_16BE}.
     */
    UTF_16BE(StandardCharsets.UTF_16BE),
    /**
     * Constant for {@link StandardCharsets#UTF_16LE}.
     */
    UTF_16LE(StandardCharsets.UTF_16LE),
    /**
     * Constant for {@link StandardCharsets#UTF_8}.
     */
    UTF_8(StandardCharsets.UTF_8);


    /**
     *
     * @param charset
     *
     * @return
     *
     * @see FieldEnums#fromFieldValue(java.lang.Class, java.lang.Object)
     */
    public static StandardCharset fromFieldValue(final Charset charset) {

        return FieldEnums.fromFieldValue(StandardCharset.class, charset);
    }


    /**
     *
     * @param fieldValues
     *
     * @see FieldEnums#fieldValues(java.lang.Class, java.util.Collection)
     * @see FieldEnums#fieldValues(java.lang.Class, java.lang.Class)
     */
    public static void fieldValues(
        final Collection<? super Charset> fieldValues) {

        FieldEnums.fieldValues(StandardCharset.class, fieldValues);
    }


    StandardCharset(final Charset fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Charset fieldValue() {

        return fieldValue;
    }


    private final Charset fieldValue;

}

