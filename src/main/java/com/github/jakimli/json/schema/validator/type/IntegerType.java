package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

class IntegerType extends SchemaType {

    IntegerType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
