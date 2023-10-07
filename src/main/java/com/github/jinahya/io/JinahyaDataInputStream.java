package com.github.jinahya.io;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 * An extended {@link DataInputStream} implements {@link JinahyaDataInput}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JinahyaDataInputStream
 */
public class JinahyaDataInputStream
        extends DataInputStream
        implements JinahyaDataInput {

    /**
     * Creates a new instance that uses the specified underlying input stream.
     *
     * @param in the underlying input stream.
     */
    public JinahyaDataInputStream(final InputStream in) {
        super(in);
    }
}
