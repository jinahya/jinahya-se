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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;

/**
 * A readable filter channel which updates a {@link MessageDigest} with bytes read.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ReadableDigestChannel extends ReadableFilterChannel {

    /**
     * Creates a new instance.
     *
     * @param channel the input channel
     */
    public ReadableDigestChannel(final ReadableByteChannel channel) {
        super(channel);
    }

    @Override
    public int read(final ByteBuffer dst) throws IOException {
        final int read = super.read(dst);
        if (read > 0 && digest != null) {
            for (int i = dst.position() - read; i < dst.position(); i++) {
                digest.update(dst.get(i));
            }
        }
        return read;
    }

    /**
     * Returns the current value of {@code digest} property.
     *
     * @return the current value of {@code digest} property; may be {@code null}.
     */
    public MessageDigest getDigest() {
        return digest;
    }

    /**
     * Replaces the current value of {@code digest} property with specified value.
     *
     * @param digest new value for {@code digest} property; {@code null} for not digesting.
     */
    public void setDigest(final MessageDigest digest) {
        this.digest = digest;
    }

    /**
     * The message digest updated with bytes read; may be {@code null}.
     */
    protected MessageDigest digest;
}
