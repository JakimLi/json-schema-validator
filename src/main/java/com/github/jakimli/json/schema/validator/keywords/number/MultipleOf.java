package com.github.jakimli.json.schema.validator.keywords.number;

import com.github.jakimli.json.schema.validator.keywords.Keyword;
import com.github.jakimli.json.schema.validator.util.NumberUtil;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jakimli.json.schema.validator.exception.BadSchemaException.badSchema;
import static com.github.jakimli.json.schema.validator.exception.SchemaViolatedException.violated;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.decimal;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.integer;
import static com.github.jakimli.json.schema.validator.util.NumberUtil.lessThanZero;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.ZERO;

public class MultipleOf implements Keyword {

    @Override
    public List<Validation> validate(String location, Object schema) {

        if (!integer(schema) && !decimal(schema)) {
            throw badSchema("multipleOf must to be numeric", schema);
        }

        if (lessThanZero(schema)) {
            throw badSchema("multipleOf must to be greater than 0", schema);
        }

        return newArrayList(assertion(instance -> {

            BigDecimal actual = NumberUtil.toDecimal(instance);
            BigDecimal factor = NumberUtil.toDecimal(schema);

            if (multipleOf(actual, factor)) {
                throw violated("expected to be multiple of: " + factor + ", got: " + actual);
            }

        }).at(location));
    }

    private boolean multipleOf(BigDecimal actual, BigDecimal factor) {
        return actual.divideAndRemainder(factor)[1].compareTo(ZERO) > 0;
    }

}
