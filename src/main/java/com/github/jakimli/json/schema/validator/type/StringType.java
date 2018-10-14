package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;

class StringType extends SchemaType {

    StringType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    void configure() {
    }
}
