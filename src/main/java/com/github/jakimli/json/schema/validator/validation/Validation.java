package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONPath;
import com.github.jakimli.json.schema.validator.assertion.Assertion;

public class Validation<T> {

    private String location;
    private Assertion<T> assertion;

    private Validation(String location, Assertion<T> assertion) {
        this.location = location;
        this.assertion = assertion;
    }

    public void validate(Object instance) {
        assertion.asserts(path(instance));
    }

    @SuppressWarnings("unchecked")
    private T path(Object instance) {
        Object validating = JSONPath.compile(location).eval(instance);
        return (T) validating;
    }

    public static class Builder<T> {
        private Assertion<T> assertion;

        Builder(Assertion<T> assertion) {
            this.assertion = assertion;
        }

        public Validation<T> at(String location) {
            return new Validation<>(location, this.assertion);
        }

        public static <T> Builder assertion(Assertion<T> assertion) {
            return new Builder<>(assertion);
        }
    }
}
