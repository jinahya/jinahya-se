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


import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaCharsets {


    /**
     * An unmodifiable list of character set names that every implementation of
     * the Java platform is required to support.
     */
    public static final List<String> SUPPORTED_CHARSET_NAMES;


    static {

        SUPPORTED_CHARSET_NAMES = Arrays.asList(
            "US-ASCII",
            "ISO-8859-1",
            "UTF-8",
            "UTF-16BE",
            "UTF-16LE",
            "UTF-16");
    }


    public static final List<Charset> SUPPORTED_CHARSETS;


    static {

        final Charset[] charsets = new Charset[SUPPORTED_CHARSET_NAMES.size()];
        for (int i = 0; i < charsets.length; i++) {
            charsets[i] = Charset.forName(SUPPORTED_CHARSET_NAMES.get(i));
        }
        SUPPORTED_CHARSETS = Arrays.asList(charsets);
    }


    private JinahyaCharsets() {

        super();
    }


}

