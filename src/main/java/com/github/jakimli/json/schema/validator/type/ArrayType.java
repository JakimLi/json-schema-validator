package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.arrayType;

class ArrayType extends AbstractType {

    ArrayType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    void configure() {
        add(validation(arrayType()));
    }
}
