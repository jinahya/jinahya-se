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


import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlRootElement
public class ImageFormatName extends ImageFeature<ImageFormatName> {


    public static ImageFormatName[] getAvailableInstances() {

        final Map<String, ImageFormatName> map = new TreeMap<>();

        for (final String value : ImageIO.getReaderFormatNames()) {
            map.put(value, new ImageFormatName().readable(true).value(value));
        }

        for (final String value : ImageIO.getWriterFormatNames()) {
            ImageFormatName instance = map.get(value);
            if (instance == null) {
                instance = new ImageFormatName().value(value);
                map.put(value, instance);
            }
            instance.setValue(value);
        }

        return map.values().toArray(new ImageFormatName[map.size()]);
    }

}

