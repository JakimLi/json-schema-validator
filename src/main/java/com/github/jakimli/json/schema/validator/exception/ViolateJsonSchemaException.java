package com.github.jakimli.json.schema.validator.exception;

public class ViolateJsonSchemaException extends ValidationException {
    public ViolateJsonSchemaException(String message, Object instance) {
        super(String.format("%s: %s", message, instance));
    }
}
