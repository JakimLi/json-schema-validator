package com.github.jakimli.json.schema.validator.exception;

public class BadSchemaException extends ValidationException {
    private BadSchemaException(String message) {
        super(message);
    }

    public static BadSchemaException badSchema(String message) {
        return new BadSchemaException(message);
    }

    public static BadSchemaException badSchema(String message, Object object) {
        return new BadSchemaException(String.format("%s, got: %s", message, object));
    }
}
