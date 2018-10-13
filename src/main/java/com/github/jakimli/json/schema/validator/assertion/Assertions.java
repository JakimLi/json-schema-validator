package com.github.jakimli.json.schema.validator.assertion;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.github.jakimli.json.schema.validator.assertion.Assertion.Builder.expect;

public class Assertions {

    public static Assertion objectType() {
        return instance -> expect(it -> it instanceof JSONObject)
                .message("expected type object")
                .test(instance);
    }

    public static Assertion stringType() {
        return instance -> expect(it -> it instanceof String)
                .message("expected type string")
                .test(instance);
    }

    public static Assertion integerType() {
        return instance -> expect(it -> it instanceof Integer)
                .message("expected type integer")
                .test(instance);
    }

    public static Assertion numberType() {
        return instance -> expect(it -> it instanceof BigDecimal)
                .message("expected type number")
                .test(instance);
    }

    public static Assertion arrayType() {
        return instance -> expect(it -> it instanceof List)
                .message("expected type array")
                .test(instance);
    }

    public static Assertion nullType() {
        return instance -> expect(Objects::isNull)
                .message("expected null")
                .test(instance);
    }
}
