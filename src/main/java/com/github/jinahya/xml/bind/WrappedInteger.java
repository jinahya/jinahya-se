/*
 * Copyright 2012 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlRootElement
public class WrappedInteger extends WrappedValue<Integer> {


    /**
     * Creates a new instance.
     *
     * @param rawValue raw value.
     *
     * @return a new instance.
     */
    public static WrappedInteger newInstance(final Integer rawValue) {

        return newInstance(WrappedInteger.class, rawValue);
    }


    @XmlElement(nillable = true, required = true)
    @Override
    public Integer getRawValue() {

        return super.getRawValue();
    }


    @Override
    public void setRawValue(final Integer rawValue) {

        super.setRawValue(rawValue);
    }

}

