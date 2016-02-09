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
import static javax.imageio.ImageIO.getReaderFormatNames;
import static javax.imageio.ImageIO.getWriterFormatNames;
import javax.xml.bind.annotation.XmlRootElement;
import static com.github.jinahya.imageio.ImageFeature.collect;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlRootElement
public class ImageFormatName extends ImageFeature<ImageFormatName> {


    public static Collection<ImageFormatName> availableImageFormatNames() {

        try {
            return collect(
                ImageFormatName.class, getReaderFormatNames(),
                getWriterFormatNames());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}

