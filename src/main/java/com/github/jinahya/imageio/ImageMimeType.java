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
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlRootElement
public class ImageMimeType extends ImageFeature {


    /**
     *
     * @return a list of available ImageMediaType.
     */
    public static Collection<ImageMimeType> getAvailableInstances() {

        final Map<String, ImageMimeType> map = new TreeMap<>();

        for (final String readerMIMEType : ImageIO.getReaderMIMETypes()) {
            ImageMimeType imageMimeType = map.get(readerMIMEType);
            if (imageMimeType == null) {
                imageMimeType = new ImageMimeType();
                map.put(readerMIMEType, imageMimeType);
            }
            imageMimeType.setReadable(true);
            imageMimeType.setValue(readerMIMEType);
        }

        for (final String writerMIMEType : ImageIO.getWriterMIMETypes()) {
            ImageMimeType imageMimeType = map.get(writerMIMEType);
            if (imageMimeType == null) {
                imageMimeType = new ImageMimeType();
                map.put(writerMIMEType, imageMimeType);
            }
            imageMimeType.setWritable(true);
            imageMimeType.setValue(writerMIMEType);
        }

        return map.values();
    }


}

