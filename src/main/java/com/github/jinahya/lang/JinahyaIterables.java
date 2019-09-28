package com.github.jinahya.lang;

import java.util.Comparator;

/**
 * A utility class for {@link Iterable}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class JinahyaIterables {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Indicates whether specified iterable is sorted by specified comparator.
     *
     * @param iterable   the iterator to check.
     * @param comparator the comparator for comparing elements.
     * @param <T>        the type of elements returned by the iterator
     * @return {@code true} if all elements in specified iterable is sorted; {@code false} otherwise.
     */
    public static <T> boolean isSorted(final Iterable<? extends T> iterable,
                                       final Comparator<? super T> comparator) {
        if (iterable == null) {
            throw new NullPointerException("iterable is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        boolean first = true;
        T previous = null; // null shouldn't be considered as the before-the-first state.
        for (final T current : iterable) {
            if (first) {
                first = false;
                previous = current;
                continue;
            }
            if (comparator.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    /**
     * Indicates whether all comparable elements of specified iterable are sorted in natural order.
     *
     * @param iterable the iterable to check.
     * @param <T>      the type of elements returned by the iterator
     * @return {@code true} if specified iterable is sorted; {@code false} otherwise.
     * @see Comparator#naturalOrder()
     * @see #isSorted(Iterable, Comparator)
     */
    public static <T extends Comparable<? super T>> boolean isSortedInNaturalOrder(
            final Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable is null");
        }
        return isSorted(iterable, Comparator.naturalOrder());
    }

    /**
     * Indicates whether all <i>comparable</i> elements in specified iterable are sorted in reverse order.
     *
     * @param iterable the iterable to check.
     * @param <T>      the type of elements returned by the iterator
     * @return {@code true} if specified iterable is sorted; {@code false} otherwise.
     * @see Comparator#reverseOrder()
     * @see #isSorted(Iterable, Comparator)
     */
    public static <T extends Comparable<? super T>> boolean isSortedInReverseOrder(
            final Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable is null");
        }
        return isSorted(iterable, Comparator.reverseOrder());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    private JinahyaIterables() {
        super();
    }
}
