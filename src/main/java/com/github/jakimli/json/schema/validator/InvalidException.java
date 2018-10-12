package com.github.jakimli.json.schema.validator;

public class InvalidException extends RuntimeException {

    private InvalidException(String message) {
        super(message);
    }
}
