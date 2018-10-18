package com.github.jakimli.json.schema.validator.keywords.number;

import java.math.BigDecimal;

import static com.github.jakimli.json.schema.validator.exception.SchemaViolatedException.violated;
import static java.lang.String.format;

public class ExclusiveMaximum extends Maximum {

    @Override
    protected void asserts(BigDecimal actual, BigDecimal maximum) {
        if (actual.compareTo(maximum) >= 0) {
            throw violated(format("expected to less than: %s, got: %s", maximum, actual));
        }
    }
}
