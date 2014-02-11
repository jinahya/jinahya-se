/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlRootElement
public class ImageMediaType extends ImageFeature {


    public static List<ImageMediaType> getAvailableInstances() {

        final List<ImageMediaType> list = new ArrayList<ImageMediaType>();

        final Map<String, ImageMediaType> map
            = new HashMap<String, ImageMediaType>();

        for (final String readerMIMEType : ImageIO.getReaderMIMETypes()) {
            ImageMediaType imageMediaType = map.get(readerMIMEType);
            if (imageMediaType == null) {
                imageMediaType = new ImageMediaType();
                map.put(readerMIMEType, imageMediaType);
            }
            imageMediaType.setReadable(true);
            imageMediaType.setValue(readerMIMEType);
        }

        for (final String writerMIMEType : ImageIO.getWriterMIMETypes()) {
            ImageMediaType imageMediaType = map.get(writerMIMEType);
            if (imageMediaType == null) {
                imageMediaType = new ImageMediaType();
                map.put(writerMIMEType, imageMediaType);
            }
            imageMediaType.setWritable(true);
            imageMediaType.setValue(writerMIMEType);
        }

        return list;
    }


}

