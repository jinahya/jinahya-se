package com.github.jinahya.io;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * An extended {@link DataOutputStream} implements {@link JinahyaDataOutput}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JinahyaDataInputStream
 */
public class JinahyaDataOutputStream
        extends DataOutputStream
        implements JinahyaDataOutput {

    /**
     * Creates a new instance that uses the specified underlying output stream.
     *
     * @param out the underlying output stream.
     */
    public JinahyaDataOutputStream(final OutputStream out) {
        super(out);
    }
}
