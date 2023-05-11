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
import java.nio.channels.Channel;
import java.util.Objects;

/**
 * An abstract channel for filtering another channel.
 *
 * @param <T> channel type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class FilterChannel<T extends Channel> implements Channel {

    /**
     * Creates a new instance on top of specified channel.
     *
     * @param channel the channel to filter.
     */
    protected FilterChannel(final T channel) {
        super();
        this.channel = Objects.requireNonNull(channel, "channel is null");
    }

    @Override
    public boolean isOpen() {
        return channel.isOpen();
    }

    @Override
    public void close() throws IOException {
        channel.close();
    }

    /**
     * The channel filtered by this channel.
     */
    protected final T channel;
}
