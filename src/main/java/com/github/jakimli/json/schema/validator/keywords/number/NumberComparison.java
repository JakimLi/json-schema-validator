package com.github.jakimli.json.schema.validator.keywords.number;

import com.github.jakimli.json.schema.validator.keywords.Keyword;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jakimli.json.schema.validator.exception.BadSchemaException.badSchema;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.decimal;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.integer;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.toDecimal;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public abstract class NumberComparison implements Keyword.Validator {

    @Override
    public List<Validation> validate(String location, Object schema) {

        if (!integer(schema) && !decimal(schema)) {
            throw badSchema(String.format("expected be numeric value, got: %s", schema));
        }

        return newArrayList(assertion(instance -> {
            BigDecimal actual = toDecimal(instance);
            BigDecimal maximum = toDecimal(schema);

            asserts(actual, maximum);

        }).at(location));
    }

    protected abstract void asserts(BigDecimal instance, BigDecimal schema);

}
