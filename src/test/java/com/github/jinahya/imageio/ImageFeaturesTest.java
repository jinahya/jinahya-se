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


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <T>
 * @param <U>
 */
public abstract class ImageFeaturesTest<T extends ImageFeatures<U>, U extends ImageFeature> {


    private static final Logger logger
        = LoggerFactory.getLogger(ImageFeaturesTest.class);


    public ImageFeaturesTest(final Class<T> imageFeaturesType) {

        super();

        this.imageFeaturesType = imageFeaturesType;
    }


    protected abstract T newImageFeaturesInstance();


    @Test
    public void marshalUnmarshal() throws JAXBException {

        final T expected = newImageFeaturesInstance();

        final JAXBContext context = JAXBContext.newInstance(
            imageFeaturesType.getPackage().getName());

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final String jaxbEncoding
            = (String) marshaller.getProperty(Marshaller.JAXB_ENCODING);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(expected, baos);

        System.out.println(new String(
            baos.toByteArray(), Charset.forName(jaxbEncoding)));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Source source
            = new StreamSource(new ByteArrayInputStream(baos.toByteArray()));
        final T actual = unmarshaller.unmarshal(source, imageFeaturesType)
            .getValue();

        for (U imageDescriptor : actual.getImageFeatureList()) {
            System.out.println("unmarshalled: " + imageDescriptor);
        }
    }


    @Test
    public void json_instance_() throws JsonMappingException, IOException {

        final ObjectMapper mapper = new ObjectMapper();
        final AnnotationIntrospector introspector
            = new JaxbAnnotationIntrospector();
        mapper.setAnnotationIntrospector(introspector);

        mapper.writeValue(System.out, newImageFeaturesInstance());
    }


    @Test
    public void json_schema_()
        throws JsonMappingException, JsonProcessingException {

        final ObjectMapper mapper = new ObjectMapper();
        final TypeFactory factory = TypeFactory.defaultInstance();
        final AnnotationIntrospector introspector
            = new JaxbAnnotationIntrospector(factory);
        mapper.setAnnotationIntrospector(introspector);

        final SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        final JavaType type = mapper.constructType(imageFeaturesType);
        mapper.acceptJsonFormatVisitor(type, visitor);
        final JsonSchema schema = visitor.finalSchema();

        final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        final String string = writer.writeValueAsString(schema);

        logger.info("{}.schema: {}", imageFeaturesType.getSimpleName(), string);
    }


    @Test
    public void json_validate_() {
    }


    private final Class<T> imageFeaturesType;


}

