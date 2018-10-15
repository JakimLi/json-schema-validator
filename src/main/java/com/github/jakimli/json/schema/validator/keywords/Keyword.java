package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

public interface Keyword {

    List<Validation> validations(String location, Object value);
}
