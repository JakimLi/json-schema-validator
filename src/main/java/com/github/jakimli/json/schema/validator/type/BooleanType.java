package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.assertion.Assertions;

class BooleanType extends SchemaType {
    BooleanType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    void configure() {
        add(validation(Assertions.booleanType()));
    }
}
