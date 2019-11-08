package com.github.jinahya.io;

import java.io.DataInputStream;
import java.io.InputStream;

public class JinahyaDataInputStream extends DataInputStream implements JinahyaDataInput {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new {@code JinahyaDataInputStream} that uses the specified underlying {@code DataInputStream}.
     *
     * @param in the specified data input stream.
     */
    public JinahyaDataInputStream(final InputStream in) {
        super(in);
    }
}
