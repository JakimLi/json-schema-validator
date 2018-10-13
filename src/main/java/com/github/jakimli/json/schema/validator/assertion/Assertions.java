package com.github.jakimli.json.schema.validator.assertion;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class Assertions {

    public static Assertion objectType() {
        return instance -> instance instanceof JSONObject;
    }

    public static Assertion stringType() {
        return instance -> instance instanceof String;
    }

    public static Assertion integerType() {
        return instance -> instance instanceof Integer;
    }

    public static Assertion numberType() {
        return instance -> instance instanceof BigDecimal;
    }

    public static Assertion arrayType() {
        return instance -> instance instanceof JSONArray;
    }
}
