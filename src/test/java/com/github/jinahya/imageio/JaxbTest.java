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


import java.io.IOException;
import java.util.Collection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class JaxbTest {


    @Test
    public static void printSchema() throws JAXBException, IOException {

        final JAXBContext context
            = JAXBContext.newInstance(JaxbTest.class.getPackage().getName());

        context.generateSchema(new SchemaOutputResolver() {

            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return new StreamResult(System.out) {

                    @Override
                    public String getSystemId() {

                        return suggestedFileName;
                    }

                };
            }

        });
    }


    static <T extends ImageFeature<T>> void printXml(final Class<T> type,
                                                     final T instances)
        throws JAXBException, IOException {

        final JAXBContext context = JAXBContext.newInstance(type);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(instances, System.out);
    }


    static <T extends ImageFeature<T>> void printXml(
        final Class<T> type, final Collection<T> instances)
        throws JAXBException, IOException {

        final JAXBContext context
            = JAXBContext.newInstance(type, ImageFeatures.class);

        final ImageFeatures<T> features = new ImageFeatures<>();
        features.getFeatures().addAll(instances);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(features, System.out);
    }

}
