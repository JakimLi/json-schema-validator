package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

class NullType extends SchemaType {
    NullType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
