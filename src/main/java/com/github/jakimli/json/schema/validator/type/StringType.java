package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.stringType;

class StringType extends AbstractType {

    StringType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    void configure() {
        add(validation(stringType()));
    }
}
