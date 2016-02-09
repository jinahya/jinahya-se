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


package com.github.jinahya.security;


import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import static java.util.Optional.ofNullable;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class CodeSources {


    /**
     *
     * @param domain
     *
     * @return
     *
     * @see ProtectionDomain#getCodeSource()
     * @see CodeSource#getLocation()
     */
    public static Optional<URL> getLocation(final ProtectionDomain domain) {

        return ofNullable(requireNonNull(domain, "null domain").getCodeSource())
            .map(CodeSource::getLocation);
    }


    /**
     *
     * @param klass
     *
     * @return
     *
     * @see Class#getProtectionDomain()
     * @see #getLocation(java.security.ProtectionDomain)
     */
    public static Optional<URL> getLocation(final Class<?> klass) {

        return getLocation(
            requireNonNull(klass, "null klass").getProtectionDomain());
    }


    private CodeSources() {

        super();
    }

}

