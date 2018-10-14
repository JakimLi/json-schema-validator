package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

class BooleanType extends Validator {
    BooleanType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
