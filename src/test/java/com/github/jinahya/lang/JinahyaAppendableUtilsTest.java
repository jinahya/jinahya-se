package com.github.jinahya.lang;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JinahyaAppendableUtilsTest {

    @DisplayName("append(appendable,c)")
    @Nested
    class AppendCharTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.append(appendable, 'a');
            assertThat(result).isSameAs(appendable).hasToString("a");
        }

        @Test
        void __nullAppendable() {
            assertThatThrownBy(() -> JinahyaAppendableUtils.append(null, 'a'))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("append(appendable,csq)")
    @Nested
    class AppendCharSequenceTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.append(appendable, "abc");
            assertThat(result).isSameAs(appendable).hasToString("abc");
        }

        @Test
        void __nullCsq() throws IOException {
            final var appendable = new StringBuilder();
            JinahyaAppendableUtils.append(appendable, (CharSequence) null);
            assertThat(appendable).hasToString("null");
        }
    }

    @DisplayName("append(appendable,csq,start,end)")
    @Nested
    class AppendSubsequenceTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.append(appendable, "xabcx", 1, 4);
            assertThat(result).isSameAs(appendable).hasToString("abc");
        }

        @Test
        void __outOfBounds() {
            assertThatThrownBy(() -> JinahyaAppendableUtils.append(new StringBuilder(), "abc", 2, 1))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @DisplayName("append(appendable,chars)")
    @Nested
    class AppendCharsTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.append(appendable, new char[] {'a', 'b'});
            assertThat(result).isSameAs(appendable).hasToString("ab");
        }

        @Test
        void __nullChars() {
            assertThatThrownBy(() -> JinahyaAppendableUtils.append(new StringBuilder(), (char[]) null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("appendCodePoint(appendable,codePoint)")
    @Nested
    class AppendCodePointTest {

        @Test
        void __bmp() throws IOException {
            final var appendable = new StringBuilder();
            JinahyaAppendableUtils.appendCodePoint(appendable, 'a');
            assertThat(appendable).hasToString("a");
        }

        @Test
        void __supplementary() throws IOException {
            final var appendable = new StringBuilder();
            JinahyaAppendableUtils.appendCodePoint(appendable, 0x1F600);
            assertThat(appendable.toString()).hasSize(2).isEqualTo(new String(Character.toChars(0x1F600)));
        }

        @Test
        void __invalid() {
            assertThatThrownBy(() -> JinahyaAppendableUtils.appendCodePoint(new StringBuilder(), -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("repeat(appendable,c,count)")
    @Nested
    class RepeatCharTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.repeat(appendable, '-', 3);
            assertThat(result).isSameAs(appendable).hasToString("---");
        }

        @Test
        void __zero() throws IOException {
            assertThat(JinahyaAppendableUtils.repeat(new StringBuilder(), '-', 0)).isEmpty();
        }

        @Test
        void __negative() {
            assertThatThrownBy(() -> JinahyaAppendableUtils.repeat(new StringBuilder(), '-', -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("repeat(appendable,csq,count)")
    @Nested
    class RepeatCharSequenceTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.repeat(appendable, "ab", 2);
            assertThat(result).isSameAs(appendable).hasToString("abab");
        }

        @Test
        void __negative() {
            assertThatThrownBy(() -> JinahyaAppendableUtils.repeat(new StringBuilder(), "ab", -1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("appendLineSeparator(appendable)")
    @Nested
    class AppendLineSeparatorTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.appendLineSeparator(appendable);
            assertThat(result).isSameAs(appendable).hasToString(System.lineSeparator());
        }
    }

    @DisplayName("join(appendable,delimiter,elements)")
    @Nested
    class JoinTest {

        @Test
        void __() throws IOException {
            final var appendable = new StringBuilder();
            final var result = JinahyaAppendableUtils.join(appendable, ",", List.of("a", "b", "c"));
            assertThat(result).isSameAs(appendable).hasToString("a,b,c");
        }

        @Test
        void __empty() throws IOException {
            assertThat(JinahyaAppendableUtils.join(new StringBuilder(), ",", List.of())).isEmpty();
        }

        @Test
        void __nullElement() throws IOException {
            final var appendable = new StringBuilder();
            JinahyaAppendableUtils.join(appendable, ",", Arrays.asList("a", null));
            assertThat(appendable).hasToString(String.join(",", Arrays.asList("a", null)));
        }

        @Test
        void __nullElements() {
            assertThatThrownBy(() -> JinahyaAppendableUtils.join(new StringBuilder(), ",", null))
                    .isInstanceOf(NullPointerException.class);
        }
    }
}
