package com.github.jakimli.json.schema.validator.exception;

public class Exceptions {
    public static InvalidJsonSchemaException invalidJsonSchema(String message) {
        return new InvalidJsonSchemaException("invalid json schema: " + message);
    }
}
