/*
 * Copyright 2014 Jin Kwon <onacit at gmail.com>.
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <T>
 * @param <F>
 */
public abstract class ImageFeaturesTest<T extends ImageFeatures<F>, F extends ImageFeature> {


    private static final Logger logger
            = LoggerFactory.getLogger(ImageFeaturesTest.class);


    public ImageFeaturesTest(final Class<T> imageFeaturesType) {

        super();

        if (imageFeaturesType == null) {
            throw new NullPointerException("null imageFeturesType");
        }

        this.imageFeaturesType = imageFeaturesType;
    }


    @Test(enabled = true)
    public void json_schema()
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

        logger.info("{}", imageFeaturesType.getSimpleName(), string);
    }


    protected final Class<T> imageFeaturesType;


}

