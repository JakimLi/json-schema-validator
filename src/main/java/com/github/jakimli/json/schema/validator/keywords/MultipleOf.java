package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validation;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jakimli.json.schema.validator.exception.BadSchemaException.badSchema;
import static com.github.jakimli.json.schema.validator.exception.SchemaViolatedException.violated;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.ZERO;

public class MultipleOf implements Keyword {

    @Override
    public List<Validation> validate(String location, Object schema) {

        if (!(schema instanceof Integer) && !(schema instanceof BigDecimal)) {
            throw badSchema("multipleOf must to be numeric", schema);
        }

        if (toDecimal(schema).compareTo(ZERO) < 0) {
            throw badSchema("multipleOf must to be greater than 0", schema);
        }

        return newArrayList(assertion(instance -> {

            BigDecimal actual = toDecimal(instance);
            BigDecimal factor = toDecimal(schema);

            if (actual.divideAndRemainder(factor)[1].compareTo(ZERO) > 0) {
                throw violated("expected to be multiple of: " + factor + ", got: " + actual);
            }

        }).at(location));
    }

    private BigDecimal toDecimal(Object value) {
        if (value instanceof Integer) {
            return BigDecimal.valueOf((Integer) value);
        }
        return (BigDecimal) value;
    }
}
