package com.github.jakimli.json.schema.validator.exception;

public class SchemaViolatedException extends ValidationException {
    private SchemaViolatedException(String message, Object instance) {
        super(String.format("%s, got: %s", message, instance));
    }

    private SchemaViolatedException(String message) {
        super(message);
    }

    public static SchemaViolatedException violated(String message) {
        return new SchemaViolatedException(message);
    }

    public static SchemaViolatedException violated(String message, Object instance) {
        return new SchemaViolatedException(message, instance);
    }
}
