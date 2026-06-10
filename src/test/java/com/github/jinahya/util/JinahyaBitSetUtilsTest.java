package com.github.jinahya.util;

/*-
 * #%L
 * jinahya-se
 * %%
 * Copyright (C) 2025 - 2026 Jinahya
 * %%
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
 * #L%
 */

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JinahyaBitSetUtilsTest {

    private static int randomIndex() {
        return current().nextInt(0, 1024);
    }

    @Nested
    class SetByte_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            final var value = current().nextInt() & 0xFF;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.setByte(set, index, value));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            final var value = current().nextInt() & 0xFF;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.setByte(set, index, value));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextInt() & 0xFF;
            assertDoesNotThrow(() -> JinahyaBitSetUtils.setByte(set, index, value));
        }
    }

    @Nested
    class GetByte_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getByte(set, index));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.getByte(set, index));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getByte(set, index));
        }
    }

    @Nested
    class Byte_Test {

        @Test
        void _Equals_SetAndGet() {
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextInt() & 0xFF;
            JinahyaBitSetUtils.setByte(set, index, value);
            assertEquals(value, JinahyaBitSetUtils.getByte(set, index));
        }
    }

    @Nested
    class SetShort_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            final var value = current().nextInt() & 0xFFFF;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.setShort(set, index, value));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            final var value = current().nextInt() & 0xFFFF;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.setShort(set, index, value));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextInt() & 0xFFFF;
            assertDoesNotThrow(() -> JinahyaBitSetUtils.setShort(set, index, value));
        }
    }

    @Nested
    class GetShort_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getShort(set, index));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.getShort(set, index));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getShort(set, index));
        }
    }

    @Nested
    class Short_Test {

        @Test
        void _Equals_SetAndGet() {
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextInt() & 0xFFFF;
            JinahyaBitSetUtils.setShort(set, index, value);
            assertEquals(value, JinahyaBitSetUtils.getShort(set, index));
        }
    }

    @Nested
    class SetInt_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            final var value = current().nextInt();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.setInt(set, index, value));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            final var value = current().nextInt();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.setInt(set, index, value));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextInt();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.setInt(set, index, value));
        }
    }

    @Nested
    class GetInt_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getInt(set, index));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.getInt(set, index));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getInt(set, index));
        }
    }

    @Nested
    class Int_Test {

        @Test
        void _Equals_SetAndGet() {
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextInt();
            JinahyaBitSetUtils.setInt(set, index, value);
            assertEquals(value, JinahyaBitSetUtils.getInt(set, index));
        }
    }

    @Nested
    class SetLong_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            final var value = current().nextLong();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.setLong(set, index, value));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            final var value = current().nextLong();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.setLong(set, index, value));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextLong();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.setLong(set, index, value));
        }
    }

    @Nested
    class GetLong_Test {

        @Test
        void _NullPointerException_SetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set = null;
            final var index = randomIndex();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getLong(set, index));
        }

        @Test
        void _IndexOutOfBoundsException_IndexIsNegative() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = -1;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(IndexOutOfBoundsException.class, () -> JinahyaBitSetUtils.getLong(set, index));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            final var index = randomIndex();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getLong(set, index));
        }
    }

    @Nested
    class Long_Test {

        @Test
        void _Equals_SetAndGet() {
            final var set = new BitSet();
            final var index = randomIndex();
            final var value = current().nextLong();
            JinahyaBitSetUtils.setLong(set, index, value);
            assertEquals(value, JinahyaBitSetUtils.getLong(set, index));
        }
    }

    @Nested
    class GetJaccardSimilarity_Test {

        @Test
        void _NullPointerException_Set1IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set1 = null;
            final var set2 = new BitSet();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getJaccardSimilarity(set1, set2));
        }

        @Test
        void _NullPointerException_Set2IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final BitSet set2 = null;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getJaccardSimilarity(set1, set2));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final var set2 = new BitSet();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getJaccardSimilarity(set1, set2));
        }

        @Test
        void _One_BothEmpty() {
            assertEquals(1.0d, JinahyaBitSetUtils.getJaccardSimilarity(new BitSet(), new BitSet()));
        }

        @Test
        void _One_SameSet() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            set.set(randomIndex());
            // --------------------------------------------------------------------------------------------- when / then
            assertEquals(1.0d, JinahyaBitSetUtils.getJaccardSimilarity(set, set));
        }

        @Test
        void _Half_KnownExample() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            set1.set(0);
            set1.set(1);
            set1.set(2);
            final var set2 = new BitSet();
            set2.set(1);
            set2.set(2);
            set2.set(3);
            // --------------------------------------------------------------------------------------------- when / then
            // intersection {1, 2} = 2, union {0, 1, 2, 3} = 4, similarity = 0.5
            assertEquals(0.5d, JinahyaBitSetUtils.getJaccardSimilarity(set1, set2));
        }
    }

    @Nested
    class GetHammingDistance_Test {

        @Test
        void _NullPointerException_Set1IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set1 = null;
            final var set2 = new BitSet();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getHammingDistance(set1, set2));
        }

        @Test
        void _NullPointerException_Set2IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final BitSet set2 = null;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getHammingDistance(set1, set2));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final var set2 = new BitSet();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getHammingDistance(set1, set2));
        }

        @Test
        void _Zero_BothEmpty() {
            assertEquals(0, JinahyaBitSetUtils.getHammingDistance(new BitSet(), new BitSet()));
        }

        @Test
        void _Zero_SameSet() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            set.set(randomIndex());
            // --------------------------------------------------------------------------------------------- when / then
            assertEquals(0, JinahyaBitSetUtils.getHammingDistance(set, set));
        }

        @Test
        void _Two_KnownExample() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            set1.set(0);
            set1.set(1);
            set1.set(2);
            final var set2 = new BitSet();
            set2.set(1);
            set2.set(2);
            set2.set(3);
            // --------------------------------------------------------------------------------------------- when / then
            // xor = {0, 3}, two positions differ
            assertEquals(2, JinahyaBitSetUtils.getHammingDistance(set1, set2));
        }
    }

    @Nested
    class GetDiceCoefficient_Test {

        @Test
        void _NullPointerException_Set1IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set1 = null;
            final var set2 = new BitSet();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getDiceCoefficient(set1, set2));
        }

        @Test
        void _NullPointerException_Set2IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final BitSet set2 = null;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getDiceCoefficient(set1, set2));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final var set2 = new BitSet();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getDiceCoefficient(set1, set2));
        }

        @Test
        void _One_BothEmpty() {
            assertEquals(1.0d, JinahyaBitSetUtils.getDiceCoefficient(new BitSet(), new BitSet()));
        }

        @Test
        void _One_SameSet() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            set.set(randomIndex());
            // --------------------------------------------------------------------------------------------- when / then
            assertEquals(1.0d, JinahyaBitSetUtils.getDiceCoefficient(set, set));
        }

        @Test
        void _Half_KnownExample() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            set1.set(0);
            set1.set(1);
            set1.set(2);
            set1.set(3);
            final var set2 = new BitSet();
            set2.set(2);
            set2.set(3);
            set2.set(4);
            set2.set(5);
            // --------------------------------------------------------------------------------------------- when / then
            // intersection = {2, 3} = 2, |set1| + |set2| = 8, dice = 2*2/8 = 0.5
            assertEquals(0.5d, JinahyaBitSetUtils.getDiceCoefficient(set1, set2));
        }
    }

    @Nested
    class GetOverlapCoefficient_Test {

        @Test
        void _NullPointerException_Set1IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set1 = null;
            final var set2 = new BitSet();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getOverlapCoefficient(set1, set2));
        }

        @Test
        void _NullPointerException_Set2IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final BitSet set2 = null;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getOverlapCoefficient(set1, set2));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final var set2 = new BitSet();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getOverlapCoefficient(set1, set2));
        }

        @Test
        void _One_BothEmpty() {
            assertEquals(1.0d, JinahyaBitSetUtils.getOverlapCoefficient(new BitSet(), new BitSet()));
        }

        @Test
        void _Zero_OneEmpty() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final var set2 = new BitSet();
            set2.set(0);
            // --------------------------------------------------------------------------------------------- when / then
            assertEquals(0.0d, JinahyaBitSetUtils.getOverlapCoefficient(set1, set2));
        }

        @Test
        void _One_SubsetCase() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            set1.set(0);
            set1.set(1);
            final var set2 = new BitSet();
            set2.set(0);
            set2.set(1);
            set2.set(2);
            set2.set(3);
            // --------------------------------------------------------------------------------------------- when / then
            // intersection = {0, 1} = 2, min(|set1|, |set2|) = min(2, 4) = 2, overlap = 2/2 = 1
            assertEquals(1.0d, JinahyaBitSetUtils.getOverlapCoefficient(set1, set2));
        }

        @Test
        void _Half_KnownExample() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            set1.set(0);
            set1.set(1);
            final var set2 = new BitSet();
            set2.set(1);
            set2.set(2);
            // --------------------------------------------------------------------------------------------- when / then
            // intersection = {1} = 1, min(|set1|, |set2|) = 2, overlap = 1/2 = 0.5
            assertEquals(0.5d, JinahyaBitSetUtils.getOverlapCoefficient(set1, set2));
        }
    }

    @Nested
    class GetCosineSimilarity_Test {

        @Test
        void _NullPointerException_Set1IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet set1 = null;
            final var set2 = new BitSet();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getCosineSimilarity(set1, set2));
        }

        @Test
        void _NullPointerException_Set2IsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final BitSet set2 = null;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.getCosineSimilarity(set1, set2));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final var set2 = new BitSet();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.getCosineSimilarity(set1, set2));
        }

        @Test
        void _One_BothEmpty() {
            assertEquals(1.0d, JinahyaBitSetUtils.getCosineSimilarity(new BitSet(), new BitSet()));
        }

        @Test
        void _Zero_OneEmpty() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            final var set2 = new BitSet();
            set2.set(0);
            // --------------------------------------------------------------------------------------------- when / then
            assertEquals(0.0d, JinahyaBitSetUtils.getCosineSimilarity(set1, set2));
        }

        @Test
        void _One_SameSet() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            set.set(randomIndex());
            // --------------------------------------------------------------------------------------------- when / then
            assertEquals(1.0d, JinahyaBitSetUtils.getCosineSimilarity(set, set));
        }

        @Test
        void _Half_KnownExample() {
            // --------------------------------------------------------------------------------------------------- given
            final var set1 = new BitSet();
            set1.set(0);
            set1.set(1);
            final var set2 = new BitSet();
            set2.set(1);
            set2.set(2);
            // --------------------------------------------------------------------------------------------- when / then
            // intersection = {1} = 1, |set1| * |set2| = 4, cosine = 1/sqrt(4) = 0.5
            assertEquals(0.5d, JinahyaBitSetUtils.getCosineSimilarity(set1, set2));
        }
    }

    @Nested
    class IsSubsetOf_Test {

        @Test
        void _NullPointerException_SubsetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final BitSet subset = null;
            final var superset = new BitSet();
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.isSubsetOf(subset, superset));
        }

        @Test
        void _NullPointerException_SupersetIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var subset = new BitSet();
            final BitSet superset = null;
            // --------------------------------------------------------------------------------------------- when / then
            assertThrows(NullPointerException.class, () -> JinahyaBitSetUtils.isSubsetOf(subset, superset));
        }

        @Test
        void _DoesNotThrow_() {
            // --------------------------------------------------------------------------------------------------- given
            final var subset = new BitSet();
            final var superset = new BitSet();
            assertDoesNotThrow(() -> JinahyaBitSetUtils.isSubsetOf(subset, superset));
        }

        @Test
        void _True_EmptySubsetOfAny() {
            // --------------------------------------------------------------------------------------------------- given
            final var subset = new BitSet();
            final var superset = new BitSet();
            superset.set(randomIndex());
            // --------------------------------------------------------------------------------------------- when / then
            assertTrue(JinahyaBitSetUtils.isSubsetOf(subset, superset));
        }

        @Test
        void _True_SelfSubsetOfSelf() {
            // --------------------------------------------------------------------------------------------------- given
            final var set = new BitSet();
            set.set(randomIndex());
            // --------------------------------------------------------------------------------------------- when / then
            assertTrue(JinahyaBitSetUtils.isSubsetOf(set, set));
        }

        @Test
        void _True_ProperSubset() {
            // --------------------------------------------------------------------------------------------------- given
            final var subset = new BitSet();
            subset.set(0);
            subset.set(1);
            final var superset = new BitSet();
            superset.set(0);
            superset.set(1);
            superset.set(2);
            // --------------------------------------------------------------------------------------------- when / then
            assertTrue(JinahyaBitSetUtils.isSubsetOf(subset, superset));
        }

        @Test
        void _False_NotSubset() {
            // --------------------------------------------------------------------------------------------------- given
            final var subset = new BitSet();
            subset.set(0);
            subset.set(1);
            subset.set(2);
            final var superset = new BitSet();
            superset.set(0);
            superset.set(1);
            // --------------------------------------------------------------------------------------------- when / then
            assertFalse(JinahyaBitSetUtils.isSubsetOf(subset, superset));
        }

        @Test
        void _False_Disjoint() {
            // --------------------------------------------------------------------------------------------------- given
            final var subset = new BitSet();
            subset.set(0);
            subset.set(1);
            final var superset = new BitSet();
            superset.set(2);
            superset.set(3);
            // --------------------------------------------------------------------------------------------- when / then
            assertFalse(JinahyaBitSetUtils.isSubsetOf(subset, superset));
        }
    }

}
