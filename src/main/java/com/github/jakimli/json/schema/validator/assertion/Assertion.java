package com.github.jakimli.json.schema.validator.assertion;

import com.github.jakimli.json.schema.validator.exception.ViolateJsonSchemaException;

import java.util.function.Predicate;

public interface Assertion {
    void asserts(Object instance) throws ViolateJsonSchemaException;

    class Builder {
        private Predicate<Object> predicate;
        private String message;

        Builder(Predicate<Object> predicate) {
            this.predicate = predicate;
        }

        static Builder expect(Predicate<Object> predicate) {
            return new Builder(predicate);
        }

        Builder message(String message) {
            this.message = message;
            return this;
        }

        void test(Object instance) {
            if (predicate.test(instance)) {
                return;
            }
            throw new ViolateJsonSchemaException(this.message, instance);
        }
    }
}
