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
package com.github.jinahya.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.security.MessageDigest;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class WritableDigestChannel extends WritableFilterChannel {

    /**
     * Creates a new instance.
     *
     * @param channel the output channel
     * @param digest the message digest to associate with this channel
     */
    public WritableDigestChannel(final WritableByteChannel channel,
            final MessageDigest digest) {

        super(channel);

        this.digest = digest;
    }

    @Override
    public int write(final ByteBuffer src) throws IOException {

        final int position = src.position();
        final int limit = src.limit();
        try {
            final int written = super.write(src);
            src.limit(src.position());
            src.position(position);
            digest.update(src);
            return written;
        } finally {
            src.limit(limit);
        }
    }

    public MessageDigest getDigest() {

        return digest;
    }

    public void setDigest(final MessageDigest digest) {

        this.digest = digest;
    }

    protected MessageDigest digest;

}
