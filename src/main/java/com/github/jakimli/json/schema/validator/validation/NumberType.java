package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

class NumberType extends Validator {
    NumberType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
