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


package com.github.jinahya.imageio;


import java.util.Collection;
import java.util.Collections;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <T>
 */
@XmlTransient
public abstract class ImageFeatures<T extends ImageFeature> {


    protected ImageFeatures(final Collection<T> imageFeatures) {

        super();

        if (imageFeatures == null) {
            throw new NullPointerException("null imageFeatures");
        }

        this.imageFeatures = Collections.unmodifiableCollection(imageFeatures);
    }


    //@XmlTransient
    protected Collection<T> getImageFeatures() {

        return imageFeatures;
    }


    //@XmlElement
    private Collection<T> imageFeatures;


}

