package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONPath;
import com.github.jakimli.json.schema.validator.assertion.Assertion;

public class Validation {

    private String location;
    private Assertion assertion;

    public Validation(String location, Assertion assertion) {
        this.location = location;
        this.assertion = assertion;
    }

    public boolean validate(Object instance) {
        return assertion.asserts(path(instance));
    }

    private Object path(Object instance) {
        return JSONPath.compile(location).eval(instance);
    }

    public static class Builder {
        private Assertion assertion;

        Builder(Assertion assertion) {
            this.assertion = assertion;
        }

        public Validation at(String location) {
            return new Validation(location, this.assertion);
        }

        public static Builder assertion(Assertion assertion) {
            return new Builder(assertion);
        }
    }
}
