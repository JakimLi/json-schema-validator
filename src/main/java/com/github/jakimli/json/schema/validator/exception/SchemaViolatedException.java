package com.github.jakimli.json.schema.validator.exception;

public class SchemaViolatedException extends ValidationException {
    public SchemaViolatedException(String message, Object instance) {
        super(String.format("%s, got: %s", message, instance));
    }
}
