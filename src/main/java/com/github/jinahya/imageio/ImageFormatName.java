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


import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlRootElement
public class ImageFormatName extends ImageFeature {


    public static Collection<ImageFormatName> getAvailableInstances() {

        final Map<String, ImageFormatName> map
                = new TreeMap<String, ImageFormatName>();

        for (final String readerFormatName : ImageIO.getReaderFormatNames()) {
            ImageFormatName imageFormatName = map.get(readerFormatName);
            if (imageFormatName == null) {
                imageFormatName = new ImageFormatName();
                map.put(readerFormatName, imageFormatName);
            }
            imageFormatName.setReadable(true);
            imageFormatName.setValue(readerFormatName);
        }

        for (final String writerFormatName : ImageIO.getWriterFormatNames()) {
            ImageFormatName imageFormatName = map.get(writerFormatName);
            if (imageFormatName == null) {
                imageFormatName = new ImageFormatName();
                map.put(writerFormatName, imageFormatName);
            }
            imageFormatName.setWritable(true);
            imageFormatName.setValue(writerFormatName);
        }

        return map.values();
    }


}

