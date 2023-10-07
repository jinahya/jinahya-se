package com.github.jinahya.io;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class DigestInputStream
        extends FunnelInputStream {

    /**
     * Creates a new instance on top of specified input stream
     *
     * @param in     the input stream.
     * @param digest a message digest; may be {@code null}.
     */
    public DigestInputStream(final InputStream in, final MessageDigest digest) {
        super(in);
        setDigest(digest);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void read(final int b) {
        super.read(b);
        if (b != -1 && !marked && digest != null) {
            digest.update((byte) b);
        }
    }

    // ------------------------------------------------------------------------------------------------------ mark/reset
    @Override
    public boolean markSupported() {
        return super.markSupported();
    }

    @Override
    public synchronized void mark(final int readlimit) {
        super.mark(readlimit);
        marked = markSupported();
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
        marked = false;
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
    private boolean marked = false;

    private MessageDigest digest;
}
