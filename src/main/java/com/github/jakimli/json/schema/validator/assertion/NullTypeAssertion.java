package com.github.jakimli.json.schema.validator.assertion;

public class NullTypeAssertion implements Assertion {

    private Object instance;

    @Override
    public boolean asserts(Object instance) {
        this.instance = instance;
        return this.instance == null;
    }

    @Override
    public String message() {
        return "expected null but not, instance: " + this.instance;
    }
}
