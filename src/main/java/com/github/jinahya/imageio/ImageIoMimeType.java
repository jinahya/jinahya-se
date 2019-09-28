package com.github.jinahya.imageio;

import javax.imageio.ImageIO;
import java.util.List;

import static javax.imageio.ImageIO.getReaderMIMETypes;
import static javax.imageio.ImageIO.getWriterMIMETypes;

/**
 * An image feature for MIME types.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderMIMETypes()
 * @see ImageIO#getWriterMIMETypes()
 */
public class ImageIoMimeType extends ImageIoFeature {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a list of all available features for MIME types.
     *
     * @return a list of image io MIME types.
     */
    public static List<ImageIoMimeType> availableImageIoMimeTypes() {
        return list(ImageIoMimeType.class, getReaderMIMETypes(), getWriterMIMETypes());
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageIoMimeType)) {
            return false;
        }
        return fieldsEqual(obj);
    }
}