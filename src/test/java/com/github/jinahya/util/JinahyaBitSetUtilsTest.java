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
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
