package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

public interface Keyword<T> {

    List<Validation> validations(String location, T value);
}