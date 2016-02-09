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


package com.github.jinahya.imageio;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


/**
 * An abstract for image features.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <T> feature type parameter
 */
abstract class ImageFeature<T extends ImageFeature<T>> {


    static <T extends ImageFeature<T>> Collection<T> collect(
        final Class<T> featureType, final String[] readerValues,
        final String[] writerValues)
        throws InstantiationException, IllegalAccessException {

        final Map<String, T> map = new HashMap<>();

        for (final String value : readerValues) {
            final T instance
                = featureType.newInstance().readable(true).value(value);
            map.put(value, instance);
        }

        for (final String value : writerValues) {
            T instance = map.get(value);
            if (instance == null) {
                instance = featureType.newInstance().value(value);
                map.put(value, instance);
            }
            instance.setWritable(true);
        }

        return map.values();
    }


    public Boolean getReadable() {

        return readable;
    }


    public void setReadable(final Boolean readable) {

        this.readable = readable;
    }


    @SuppressWarnings("unchecked")
    public T readable(final Boolean readable) {

        setReadable(readable);

        return (T) this;
    }


    public Boolean getWritable() {

        return writable;
    }


    public void setWritable(final Boolean writable) {

        this.writable = writable;
    }


    @SuppressWarnings("unchecked")
    public T writable(final Boolean writable) {

        setWritable(writable);

        return (T) this;
    }


    public String getValue() {

        return value;
    }


    public void setValue(final String value) {

        this.value = value;
    }


    @SuppressWarnings("unchecked")
    public T value(final String value) {

        setValue(value);

        return (T) this;
    }


    @Override
    public String toString() {

        return super.toString()
               + "?readable=" + readable
               + "&writable=" + writable
               + "&value=" + value;
    }


    @XmlAttribute
    private Boolean readable;


    @XmlAttribute
    private Boolean writable;


    @XmlValue
    private String value;

}

