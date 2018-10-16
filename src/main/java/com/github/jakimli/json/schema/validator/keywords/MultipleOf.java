package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validation;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.multipleOf;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public class MultipleOf implements Keyword {

    @Override
    public List<Validation> validate(String location, Object value) {
        if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            return newArrayList(assertion(multipleOf(decimal)).at(location));
        }

        return newArrayList(assertion(multipleOf((Integer) value)).at(location));
    }
}
