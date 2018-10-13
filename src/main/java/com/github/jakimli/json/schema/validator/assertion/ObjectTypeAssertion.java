package com.github.jakimli.json.schema.validator.assertion;

import static com.alibaba.fastjson.JSON.toJSONString;

public class ObjectTypeAssertion implements Assertion {
    private Object instance;

    @Override
    public boolean asserts(Object instance) {
        this.instance = instance;
        if (instance instanceof String) {
            return startWithCurlyBrace((String) instance);
        }
        return startWithCurlyBrace(toJSONString(instance));
    }

    private boolean startWithCurlyBrace(String s) {
        return s.trim().startsWith("{");
    }

    @Override
    public String message() {
        return "Not an object type: " + instance;
    }
}
