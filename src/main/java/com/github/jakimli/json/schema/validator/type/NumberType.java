package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.numberType;

class NumberType extends SchemaType {
    NumberType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    void configure() {
        add(validation(numberType()));
    }
}
