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


package com.github.jinahya.util;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JinahyaArrays {


    public static void reverse(final short[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final short t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final boolean[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final boolean t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final byte[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final byte t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final char[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final char t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final double[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final double t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final float[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final float t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final int[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final long[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final long t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void reverse(final Object[] a) {

        final int h = a.length / 2;
        for (int i = 0, j = a.length - 1; i < h; i++, j--) {
            final Object t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    private JinahyaArrays() {

        super();
    }


}

