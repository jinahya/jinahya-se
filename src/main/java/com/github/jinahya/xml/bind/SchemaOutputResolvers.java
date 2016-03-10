/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.xml.bind;

import java.io.IOException;
import java.util.function.BiFunction;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class SchemaOutputResolvers {

    @FunctionalInterface
    public static interface Functional {

        /**
         *
         * @param namespaceUri
         * @param suggestedFileName
         *
         * @return
         *
         * @throws IOException
         *
         * @see SchemaOutputResolver#createOutput(java.lang.String,
         * java.lang.String)
         */
        Result createOutput(String namespaceUri, String suggestedFileName)
                throws IOException;

    }

    @FunctionalInterface
    public static interface Functional2
            extends Functional, BiFunction<String, String, Result> {

        @Override
        default Result apply(final String namespaceUri,
                final String suggestedFileName) {
            try {
                return createOutput(namespaceUri, suggestedFileName);
            } catch (final IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }

    }

    /**
     *
     * @param functional
     *
     * @return
     */
    public static SchemaOutputResolver of(final Functional functional) {

        return new SchemaOutputResolver() {

            @Override
            public Result createOutput(final String namespaceUri,
                    final String suggestedFileName)
                    throws IOException {

                return functional.createOutput(namespaceUri, suggestedFileName);
            }

        };
    }

    private SchemaOutputResolvers() {

        super();
    }

}
