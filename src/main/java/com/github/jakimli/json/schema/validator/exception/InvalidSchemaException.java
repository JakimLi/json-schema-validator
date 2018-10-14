package com.github.jakimli.json.schema.validator.exception;

public class InvalidSchemaException extends ValidationException {
    private InvalidSchemaException(String message) {
        super(message);
    }

    public static InvalidSchemaException invalidSchema(String message) {
        return new InvalidSchemaException(message);
    }

    public static InvalidSchemaException invalidSchema(String message, Object object) {
        return new InvalidSchemaException(String.format("%s, got: %s", message, object));
    }
}
