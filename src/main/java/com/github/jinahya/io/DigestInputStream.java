package com.github.jinahya.io;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class DigestInputStream extends FunnelInputStream {

    public DigestInputStream(final InputStream stream) {
        super(stream);
    }

    @Override
    public int read() throws IOException {
        final int b = super.read();
        if (digest != null && b != -1) {
            digest.update((byte) b);
        }
        return b;
    }

    public MessageDigest getDigest() {
        return digest;
    }

    public void setDigest(final MessageDigest digest) {
        this.digest = digest;
    }

    private MessageDigest digest;
}
