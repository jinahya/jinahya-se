/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.util.Comparator;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T>
 */
public class ComparableComparator<T extends Comparable<T>>
    implements Comparator<T> {


    @Override
    public int compare(final T o1, final T o2) {

        if (nullPrecedesNonNulls == null) {
            return o1.compareTo(o2);
        }

        if (nullPrecedesNonNulls.booleanValue()) {
            return o1 == null
                   ? (o2 == null ? 0 : -1)
                   : (o2 == null ? 1 : o1.compareTo(o2));

        } else {
            return o1 == null
                   ? (o2 == null ? 0 : 1)
                   : (o2 == null ? -1 : o1.compareTo(o2));

        }
    }


    public Boolean getNullPrecedesNonNulls() {

        return nullPrecedesNonNulls;
    }


    public void setNullPrecedesNonNulls(final Boolean nullPrecedesNonNulls) {

        this.nullPrecedesNonNulls = nullPrecedesNonNulls;
    }


    private Boolean nullPrecedesNonNulls;


}

