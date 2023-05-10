package com.github.jinahya.io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

public class DigestOutputStream extends FunnelOutputStream {

    public DigestOutputStream(final OutputStream stream) {
        super(stream);
    }

    @Override
    public void write(final int b) throws IOException {
        super.write(b);
        if (digest != null) {
            digest.update((byte) b);
        }
    }

    public MessageDigest getDigest() {
        return digest;
    }

    public void setDigest(final MessageDigest digest) {
        this.digest = digest;
    }

    private MessageDigest digest;
}
