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
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class SchemaOutputResolvers {


    @FunctionalInterface
    public static interface Functional {


        Result createOutput(String namespaceUri, String suggestedFileName)
            throws IOException;


    }


    private static final class FunctionalAdapter extends SchemaOutputResolver {


        public FunctionalAdapter(final Functional functional) {

            super();

            this.functional = functional;
        }


        @Override
        public Result createOutput(final String namespaceUri,
                                   final String suggestedFileName)
            throws IOException {

            return functional.createOutput(namespaceUri, suggestedFileName);
        }


        private final Functional functional;


    }


    public static SchemaOutputResolver of(final Functional funcational) {

        return new FunctionalAdapter(funcational);
    }


    private SchemaOutputResolvers() {

        super();
    }


}

