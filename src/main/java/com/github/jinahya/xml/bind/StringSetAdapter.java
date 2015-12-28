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


package com.github.jinahya.xml.bind;


import java.util.HashSet;
import java.util.Set;


/**
 * An abstract XmlAdapter for marshalling sets to concatenated strings with
 * specific delimiter.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class StringSetAdapter<E>
    extends StringCollectionAdapter<Set<E>, E> {


    public StringSetAdapter(final String delimiter) {

        super(delimiter);
    }


    @Override
    protected Set<E> bound(final String value) {

        return new HashSet<E>();
    }

}

