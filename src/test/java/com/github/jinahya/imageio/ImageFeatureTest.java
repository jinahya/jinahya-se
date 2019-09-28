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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

/**
 * @param <T> image feature type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class ImageFeatureTest<T extends ImageIoFeature> {

    /**
     * Creates a new instance.
     *
     * @param type image feature type.
     */
    public ImageFeatureTest(final Class<T> type) {
        super();
        this.type = requireNonNull(type, "null type");
    }

    @Test
    public void JsonSchema()
            throws JsonMappingException, JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final TypeFactory factory = TypeFactory.defaultInstance();
        final AnnotationIntrospector introspector
                = new JaxbAnnotationIntrospector(factory);
        mapper.setAnnotationIntrospector(introspector);
        final SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        final JavaType javaType = mapper.constructType(type);
        mapper.acceptJsonFormatVisitor(javaType, visitor);
        final JsonSchema schema = visitor.finalSchema();
        final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        final String string = writer.writeValueAsString(schema);
        logger.info("{}.schema: {}", type.getSimpleName(), string);
    }

    @Test
    public void XmlSchema() throws JAXBException, IOException {
        final JAXBContext context = JAXBContext.newInstance(type);
        context.generateSchema(new SchemaOutputResolver() {

            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                    throws IOException {
                return new StreamResult(System.out) {
                    public String getSystemId() {
                        return "";
                    }
                };
            }
        });
    }

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final Class<T> type;
}
