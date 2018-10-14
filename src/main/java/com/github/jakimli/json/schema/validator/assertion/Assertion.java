package com.github.jakimli.json.schema.validator.assertion;

import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;

import java.util.function.Predicate;

public interface Assertion<T> {
    void asserts(T instance) throws SchemaViolatedException;

    class AssertionBuilder<T> {
        private Predicate<T> predicate;
        private String message;

        private AssertionBuilder(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        public static <T> AssertionBuilder<T> expect(Predicate<T> predicate) {
            return new AssertionBuilder<>(predicate);
        }

        AssertionBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        void test(T instance) {
            if (predicate.test(instance)) {
                return;
            }
            throw new SchemaViolatedException(this.message, instance);
        }

        public void test(T instance, RuntimeException exception) {
            if (predicate.test(instance)) {
                return;
            }
            throw exception;
        }
    }
}
