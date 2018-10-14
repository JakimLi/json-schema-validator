package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

class IntegerType extends Validator {

    IntegerType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
