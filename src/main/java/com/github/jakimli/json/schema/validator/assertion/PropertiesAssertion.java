package com.github.jakimli.json.schema.validator.assertion;

import com.alibaba.fastjson.JSONObject;

import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;

public class PropertiesAssertion implements Assertion {

    private JSONObject properties;

    public PropertiesAssertion(Object properties) {
        if (!(properties instanceof JSONObject)) {
            throw invalidSchema("properties must be json object: " + properties);
        }
        this.properties = (JSONObject) properties;
    }

    @Override
    public boolean asserts(Object instance) {
        return true;
    }
}
