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
import static javax.imageio.ImageIO.getReaderFileSuffixes;
import static javax.imageio.ImageIO.getWriterFileSuffixes;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlRootElement
public class ImageFileSuffix extends ImageFeature<ImageFileSuffix> {

    public static Collection<ImageFileSuffix> availableImageFileSuffix() {

        try {
            return collect(
                    ImageFileSuffix.class, getReaderFileSuffixes(),
                    getWriterFileSuffixes());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
