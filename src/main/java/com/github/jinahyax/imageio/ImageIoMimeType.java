package com.github.jinahyax.imageio;

import javax.imageio.ImageIO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static javax.imageio.ImageIO.getReaderMIMETypes;
import static javax.imageio.ImageIO.getWriterMIMETypes;

/**
 * An image io feature for MIME types.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderMIMETypes()
 * @see ImageIO#getWriterMIMETypes()
 */
public final class ImageIoMimeType extends ImageIoFeature {

    /**
     * Returns a list of all available features for MIME types.
     *
     * @return a list of image io MIME types.
     */
    public static List<ImageIoMimeType> availableImageIoMimeTypes() {
        return list(
                ImageIoMimeType.class,
                new HashSet<>(Arrays.asList(getReaderMIMETypes())),
                new HashSet<>(Arrays.asList(getWriterMIMETypes()))
        );
    }

    private ImageIoMimeType(final String value, final boolean readable, final boolean writable) {
        super(value, readable, writable);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ImageIoMimeType)) return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
