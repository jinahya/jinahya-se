/*
 * Copyright 2014 <a href="mailto:onacit@gmail.com">Jin Kwon</a>.
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


package com.github.jinahya.lang;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public final class Strings {


    /**
     *
     * @param string
     * @param ch
     * @param fromIndex
     * @param ordinal
     *
     * @return
     *
     * @see String#indexOf(int, int)
     */
    public static int indexOf(final String string, final int ch, int fromIndex,
                              int ordinal) {

        if (string == null) {
            throw new NullPointerException("string == null");
        }

        if (ordinal < 0) {
            throw new IllegalArgumentException("ordinal(" + ordinal + ") < 0");
        }

        int index = -1;

        for (; ordinal >= 0; ordinal--) {
            index = string.indexOf(ch, fromIndex);
            if (index == -1) {
                break;
            }
            fromIndex = index + 1;
        }

        return index;
    }


    public static List<Integer> indicesOf(final String string, final int ch,
                                          int fromIndex) {

        if (string == null) {
            throw new NullPointerException("string == null");
        }

        final List<Integer> indices = new ArrayList<Integer>();

        while (true) {
            final int index = string.indexOf(ch, fromIndex);
            if (index == -1) {
                break;
            }
            indices.add(index);
            fromIndex = index + 1;
        }

        return indices;
    }


    /**
     *
     * @param string
     * @param str
     * @param fromIndex
     * @param ordinal
     *
     * @return
     *
     * @see String#indexOf(java.lang.String, int)
     */
    public static int indexOf(final String string, final String str,
                              int fromIndex, int ordinal) {

        if (string == null) {
            throw new NullPointerException("string == null");
        }

        if (ordinal < 0) {
            throw new IllegalArgumentException("ordinal(" + ordinal + ") < 0");
        }

        int index = -1;

        for (; ordinal >= 0; ordinal--) {
            index = string.indexOf(str, fromIndex);
            if (index == -1) {
                break;
            }
            fromIndex = index + str.length();
        }

        return index;
    }


    public static List<Integer> indicesOf(final String string, final String str,
                                          int fromIndex) {

        if (string == null) {
            throw new NullPointerException("string == null");
        }

        final List<Integer> indices = new ArrayList<Integer>();


        while (true) {
            final int index = string.indexOf(str, fromIndex);
            if (index == -1) {
                break;
            }
            indices.add(index);
            fromIndex = index + str.length();
        }

        return indices;
    }


    private Strings() {

        super();
    }


}

