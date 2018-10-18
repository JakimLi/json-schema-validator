package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.keywords.Keyword.Validator;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.mustBe;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public class Const implements Validator {

    @Override
    public List<Validation> validate(String location, Object value) {
        return newArrayList(assertion(mustBe(value)).at(location));
    }
}
