package com.github.jakimli.json.schema.validator.assertion;

import com.alibaba.fastjson.JSONObject;

public class ObjectTypeAssertion implements Assertion {
    @Override
    public boolean asserts(Object instance) {
        return instance instanceof JSONObject;
    }
}
