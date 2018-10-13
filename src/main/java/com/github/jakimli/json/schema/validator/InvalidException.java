package com.github.jakimli.json.schema.validator;

import com.github.jakimli.json.schema.validator.assertion.Assertion;

public class InvalidException extends RuntimeException {

    public InvalidException(Assertion assertion) {
        super(assertion.message());
    }
}
