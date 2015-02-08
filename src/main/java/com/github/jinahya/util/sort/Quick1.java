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


package com.github.jinahya.util.sort;


/**
 *
 * @author Jin Kwon
 * @param <T>
 */
public class Quick1<T extends Comparable<T>> implements Sort<T> {


    private void swap(final T[] a, final int i, final int j) {

        final T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    private int partition(final T[] a, final int l, final int r, final int p) {

        final T v = a[p];
        swap(a, p, r);

        int s = l;
        for (int i = l; i < r; i++) {
            if (a[i].compareTo(v) <= 0) {
                swap(a, s++, i);
            }
        }
        swap(a, s, r);

        return s;
    }


    private void sort(final T[] a, final int l, final int r) {

        if (l >= r) {
            return;
        }

        final int p = partition(a, l, r, (l + r) / 2);

        sort(a, l, p - 1);
        sort(a, p + 1, r);
    }


    @Override
    public void sort(final T[] a) {

        sort(a, 0, a.length - 1);
    }


    private void swap(final byte[] a, final int i, final int j) {

        final byte t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    private int partition(final byte[] a, final int l, final int r, final int p) {

        final byte v = a[p];
        swap(a, p, r);

        int s = l;
        for (int i = l; i < r; i++) {
            if (a[i] <= v) {
                swap(a, s++, i);
            }
        }
        swap(a, s, r);

        return s;
    }


    private void sort(final byte[] a, final int l, final int r) {

        if (l >= r) {
            return;
        }

        final int p = partition(a, l, r, (l + r) / 2);

        sort(a, l, p - 1);
        sort(a, p + 1, r);
    }


    public void sort(final byte[] a) {

        sort(a, 0, a.length - 1);
    }


}

