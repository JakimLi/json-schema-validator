package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

class NumberType extends SchemaType {
    NumberType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    void configure() {
    }
}
