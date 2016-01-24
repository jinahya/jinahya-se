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
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaFileChannels {


    private static class BufferedChannel extends FilterReadableByteChannel {


        @Override
        public int read(final ByteBuffer dst) throws IOException {
            if (dst.remaining() == 0) {
                return 0;
            }
            if (buf != null) {
                dst.put(buf);
                return 1;
            }
            return super.read(dst);
        }


        private BufferedChannel(final ReadableByteChannel channel) {
            super(channel);
        }


        private ByteBuffer buf;

    }


    public static long transferFrom(final FileChannel dst,
                                    ReadableByteChannel src,
                                    long position, long count)
        throws IOException {

        if (position > dst.size()) {
            return 0L;
        }

        long accumulated = 0L;

        while (count > 0L) {
            final long transferred = dst.transferFrom(src, position, count);
            if (transferred == 0) {
                final ByteBuffer buf = ByteBuffer.allocate(1);
                final int read = src.read(buf);
                if (read == -1) {
                    break;
                }
                buf.flip();
                if (buf.remaining() > 0) {
                    src = new BufferedChannel(src);
                    ((BufferedChannel) src).buf = buf;
                }
            }
            position += transferred;
            count -= transferred;
            // not gonna break if src is shorter than count
        }

        return accumulated;
    }


    public static long transferTo(final FileChannel src, long position,
                                  long count, final WritableByteChannel dst)
        throws IOException {

        long accumulated = 0L;

        while (position < src.size()) {
            final long transferred = src.transferTo(position, count, dst);
            position += transferred;
            count -= transferred;
            accumulated += transferred;
        }

        return accumulated;
    }


    private JinahyaFileChannels() {

        super();
    }

}

