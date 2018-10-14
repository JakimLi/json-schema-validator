package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

class BooleanType extends SchemaType {
    BooleanType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
