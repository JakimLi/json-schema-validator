package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validation;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jakimli.json.schema.validator.exception.SchemaViolatedException.violated;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.RoundingMode.UNNECESSARY;

public class MultipleOf implements Keyword {

    @Override
    public List<Validation> validate(String location, Object value) {

        return newArrayList(assertion(instance -> {
            if (instance instanceof Integer && value instanceof Integer
                    && (Integer) instance % (Integer) value != 0) {

                throw violated("expected to be multiple of: " + value + ", got: " + instance);
            }

            BigDecimal actual = toDecimal(instance);
            BigDecimal factor = toDecimal(value);

            if (actual.divide(factor, UNNECESSARY).stripTrailingZeros().scale() > 0) {
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
