package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

class ArrayType extends SchemaType {

    ArrayType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
