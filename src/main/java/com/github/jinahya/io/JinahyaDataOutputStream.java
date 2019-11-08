package com.github.jinahya.io;

import java.io.DataOutputStream;

public class JinahyaDataOutputStream extends DataOutputStream implements JinahyaDataOutput {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new {@code JinahyaDataOutputStream} that uses uses the specified underlying {@code DataOutputStream}.
     *
     * @param out the specified data output stream.
     */
    public JinahyaDataOutputStream(final DataOutputStream out) {
        super(out);
    }
}
