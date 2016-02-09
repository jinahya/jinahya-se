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


import static com.github.jinahya.imageio.ImageFormatName.availableImageFormatNames;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ImageFormatNameTest extends ImageFeatureTest<ImageFormatName> {


    /**
     * Creates a new instance.
     */
    public ImageFormatNameTest() {

        super(ImageFormatName.class);
    }


    @Test
    public void printXml() throws JAXBException, IOException {

        JaxbTest.printXml(type, availableImageFormatNames());
    }

}
