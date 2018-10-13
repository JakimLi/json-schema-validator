package com.github.jakimli.json.schema.validator.exception;

public class InvalidKeywordException extends ValidationException {

    private InvalidKeywordException(String message) {
        super(message);
    }

    public static InvalidKeywordException invalidKeywordException(String keyword) {
        return new InvalidKeywordException("Invalid keyword: " + keyword);
    }
}
