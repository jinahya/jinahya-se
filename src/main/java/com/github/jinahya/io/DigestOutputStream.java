package com.github.jinahya.io;

import java.io.OutputStream;
import java.security.MessageDigest;

public class DigestOutputStream
        extends FunnelOutputStream {

    public DigestOutputStream(final OutputStream out, final MessageDigest digest) {
        super(out);
        setDigest(digest);
    }

    @Override
    protected void written(final int b) {
        if (digest != null) {
            digest.update((byte) b);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@code digest} property.
     *
     * @return current value of the {@code digest} property.
     */
    public MessageDigest getDigest() {
        return digest;
    }

    /**
     * Replaces current value of {@code digest} property with specified value.
     *
     * @param digest new value for the {@code digest} property; may be {@code null}.
     */
    public void setDigest(final MessageDigest digest) {
        this.digest = digest;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private MessageDigest digest;
}
