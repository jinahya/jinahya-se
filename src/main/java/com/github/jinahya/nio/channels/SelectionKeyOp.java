package com.github.jinahya.nio.channels;

import com.github.jinahya.lang.IntFieldEnum;

import java.nio.channels.SelectionKey;

/**
 * Constants for fields of {@code OP_...} defined in {@link SelectionKey} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum SelectionKeyOp
        implements IntFieldEnum<SelectionKeyOp> {

    /**
     * A constant for {@link SelectionKey#OP_READ} field.
     */
    OP_READ(SelectionKey.OP_READ),

    /**
     * A constant for {@link SelectionKey#OP_WRITE} field.
     */
    OP_WRITE(SelectionKey.OP_WRITE),

    /**
     * A constant for {@link SelectionKey#OP_CONNECT} field.
     */
    OP_CONNECT(SelectionKey.OP_CONNECT),

    /**
     * A constant for {@link SelectionKey#OP_ACCEPT} field.
     */
    OP_ACCEPT(SelectionKey.OP_ACCEPT);

    public static SelectionKeyOp valueOfFieldValue(final int fieldValue) {
        return IntFieldEnum.valueOfFieldValue(SelectionKeyOp.class, fieldValue);
    }

    SelectionKeyOp(final int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int getFieldValue() {
        return fieldValue;
    }

    private final int fieldValue;
}
