package com.github.jinahya.beans;

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
