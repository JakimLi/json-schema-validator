package com.github.jakimli.json.schema.validator.util;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class NumberUtil {

    public static BigDecimal toDecimal(Object value) {
        return integer(value) ? BigDecimal.valueOf((Integer) value) : (BigDecimal) value;
    }

    public static boolean integer(Object schema) {
        return schema instanceof Integer;
    }

    public static boolean lessThanZero(Object schema) {
        return toDecimal(schema).compareTo(ZERO) < 0;
    }

    public static boolean decimal(Object schema) {
        return schema instanceof BigDecimal;
    }
}
