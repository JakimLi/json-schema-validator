package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validation;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jakimli.json.schema.validator.exception.BadSchemaException.badSchema;
import static com.github.jakimli.json.schema.validator.exception.SchemaViolatedException.violated;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.decimal;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.integer;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.toDecimal;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public class Maximum implements Keyword {
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

    protected void asserts(BigDecimal actual, BigDecimal maximum) {
        if (actual.compareTo(maximum) > 0) {
            throw violated(format("expected to less than or equals to: %s, got: %s", maximum, actual));
        }
    }
}
