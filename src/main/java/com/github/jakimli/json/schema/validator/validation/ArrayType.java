package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

class ArrayType extends Validator {

    ArrayType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
    }
}
