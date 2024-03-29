/*
 * Copyright 2016 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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
package com.github.jinahya.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

/**
 * A readable byte channel filtering another channel.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see WritableFilterChannel
 */
public class ReadableFilterChannel
        extends FilterChannel<ReadableByteChannel>
        implements ReadableByteChannel {

    /**
     * Creates a new instance on top of specified channel.
     *
     * @param channel the channel to filter.
     */
    public ReadableFilterChannel(final ReadableByteChannel channel) {
        super(channel);
    }

    @Override
    public int read(final ByteBuffer dst) throws IOException {
        Objects.requireNonNull(dst, "dst is null");
        return channel.read(dst);
    }
}
