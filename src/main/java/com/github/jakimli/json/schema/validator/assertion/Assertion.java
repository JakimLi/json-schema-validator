package com.github.jakimli.json.schema.validator.assertion;

import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;

import java.util.function.Predicate;

public interface Assertion<T> {
    void asserts(T instance) throws SchemaViolatedException;

    interface ExceptionSupplier {
        RuntimeException get(Object instance);
    }

    class AssertionBuilder<T> {
        private Predicate<T> predicate;
        private ExceptionSupplier exception;

        private AssertionBuilder(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        static <T> AssertionBuilder<T> expect(Predicate<T> predicate) {
            return new AssertionBuilder<>(predicate);
        }

        AssertionBuilder<T> toThrow(ExceptionSupplier exception) {
            this.exception = exception;
            return this;
        }

        public void test(T instance) {
            if (predicate.test(instance)) {
                return;
            }
            throw exception.get(instance);
        }
    }
}
