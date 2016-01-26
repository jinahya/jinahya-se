/*
 * Copyright 2014 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.util.function;


/**
 * Represents a predicate (boolean-valued function) of one boolean-valued
 * argument. This is the boolean-consuming primitive type specialization of
 * {@link java.util.function.Predicate}.
 * <p/>
 * This is a
 * <a href="http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">functional
 * interface</a> whose functional method is {@link #test(boolean)}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface BooleanPredicate {


    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     *
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(boolean value);


    /**
     * Returns a predicate that represents the logical negation of this
     * predicate
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default BooleanPredicate negate() {

        return v -> !test(v);
    }


    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another. When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p/>
     * Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     *
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the other predicate
     *
     * @throws NullPointerException if {@code other} is {@code null}
     */
    default BooleanPredicate and(final BooleanPredicate other) {

        return v -> test(v) && other.test(v);
    }


    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another. When evaluating the composed predicate,
     * if this predicate is {@code true}, then the {@code other} predicate is
     * not evaluated.
     * <p/>
     * Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     *
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the other predicate
     *
     * @throws NullPointerException if {@code other} is {@code null}
     */
    default BooleanPredicate or(final BooleanPredicate other) {

        return v -> test(v) || other.test(v);
    }


}

