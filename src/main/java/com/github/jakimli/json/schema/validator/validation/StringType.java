package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

class StringType extends Validator {

    StringType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
