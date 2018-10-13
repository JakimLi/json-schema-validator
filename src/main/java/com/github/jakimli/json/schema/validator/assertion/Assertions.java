package com.github.jakimli.json.schema.validator.assertion;

public class Assertions {

    public static Assertion objectType() {
        return new ObjectTypeAssertion();
    }
}
