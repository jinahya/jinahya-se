package com.github.jinahya.beans;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
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
 * #L%
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.net.NetworkInterface;
import java.net.SocketException;

@Slf4j
class JinahyaBeansUtils2Test {

    @Nested
    class NetworkInterfacesTest {

        @Test
        void __() throws SocketException, IntrospectionException {
            for (final var e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                final var networkInterface = e.nextElement();
                log.debug("networkInterface: {}", networkInterface);
                JinahyaBeansUtils2.acceptEachPropertyValue(
                        networkInterface,
                        null,
                        b -> a -> d -> v -> {
                            log.debug("\t{}: {}", d.getName(), v);
                        }
                );
            }
        }
    }
}
