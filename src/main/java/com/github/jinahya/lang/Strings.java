/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for {@link String}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class Strings {

    /**
     * Concatenates specified strings to specified string.
     *
     * @param string the string to which others are concatenated.
     * @param strs   the strings to concatenate.
     * @return a string of {@code string} followed by each of {@code strs}.
     * @throws NullPointerException if {@code string} or {@code strs} is {@code null}.
     * @see String#concat(String)
     */
    public static String concat(String string, final String... strs) {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        if (strs == null) {
            throw new NullPointerException("null strs");
        }

        for (final String str : strs) {
            string = string.concat(str);
        }

        return string;
    }

    /**
     * Tests if specified string contains any of specified character sequences.
     *
     * @param string the string to test.
     * @param ss     the character sequences to search for.
     * @return {@code true} if {@code string} contains any of {@code ss}; {@code false} otherwise.
     * @throws NullPointerException if {@code string} or {@code ss} is {@code null}.
     * @see String#contains(CharSequence)
     */
    public static boolean containsAny(final String string, final CharSequence... ss) {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        if (ss == null) {
            throw new NullPointerException("null ss");
        }

        for (final CharSequence s : ss) {
            if (string.contains(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests if specified string contains all of specified character sequences.
     *
     * @param string the string to test.
     * @param ss     the character sequences to search for.
     * @return {@code true} if {@code string} contains all of {@code ss}; {@code false} otherwise.
     * @throws NullPointerException if {@code string} or {@code ss} is {@code null}.
     * @see String#contains(CharSequence)
     */
    public static boolean containsAll(final String string, final CharSequence... ss) {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        if (ss == null) {
            throw new NullPointerException("null ss");
        }

        for (final CharSequence s : ss) {
            if (!string.contains(s)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Test if give string ends with any of specified suffixes.
     *
     * @param string   the string to test
     * @param suffixes suffixes
     * @return {@code true} if given string ends with any of specified suffixes; {@code false} otherwise.
     * @see String#endsWith(java.lang.String)
     */
    public static boolean endsWith(final String string,
                                   final String... suffixes) {

        if (string == null) {
            throw new NullPointerException("null string");
        }

        if (suffixes == null) {
            throw new NullPointerException("null suffixes");
        }

        for (final String suffix : suffixes) {
            if (string.endsWith(suffix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the index of the {@code ordinal}-th (zero-based) occurrence of specified character in specified string,
     * starting the search at specified index.
     *
     * @param string    the string to search.
     * @param ch        the character to search for.
     * @param fromIndex the index from which to start the search.
     * @param ordinal   the zero-based ordinal of the occurrence.
     * @return the index of the {@code ordinal}-th occurrence of {@code ch}; {@code -1} if no such occurrence.
     * @throws NullPointerException     if {@code string} is {@code null}.
     * @throws IllegalArgumentException if {@code ordinal} is negative.
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

    /**
     * Returns a list of indices of all occurrences of specified character in specified string, starting the search at
     * specified index.
     *
     * @param string    the string to search.
     * @param ch        the character to search for.
     * @param fromIndex the index from which to start the search.
     * @return a list of indices of all occurrences of {@code ch}.
     * @throws NullPointerException if {@code string} is {@code null}.
     * @see String#indexOf(int, int)
     */
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
     * Returns the index of the {@code ordinal}-th (zero-based) non-overlapping occurrence of specified substring in
     * specified string, starting the search at specified index.
     *
     * @param string    the string to search.
     * @param str       the substring to search for.
     * @param fromIndex the index from which to start the search.
     * @param ordinal   the zero-based ordinal of the occurrence.
     * @return the index of the {@code ordinal}-th occurrence of {@code str}; {@code -1} if no such occurrence.
     * @throws NullPointerException     if {@code string} is {@code null}.
     * @throws IllegalArgumentException if {@code ordinal} is negative.
     * @see String#indexOf(String, int)
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

    /**
     * Returns a list of indices of all non-overlapping occurrences of specified substring in specified string,
     * starting the search at specified index.
     *
     * @param string    the string to search.
     * @param str       the substring to search for.
     * @param fromIndex the index from which to start the search.
     * @return a list of indices of all occurrences of {@code str}.
     * @throws NullPointerException if {@code string} is {@code null}.
     * @see String#indexOf(String, int)
     */
    public static List<Integer> indicesOf(final String string, final String str,
                                          int fromIndex) {

        if (string == null) {
            throw new NullPointerException("string == null");
        }

        final List<Integer> indices = new ArrayList<>();

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
