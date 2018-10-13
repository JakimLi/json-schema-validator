package com.github.jakimli.json.schema.validator;

import static com.alibaba.fastjson.JSON.parse;
import static com.alibaba.fastjson.JSON.parseObject;

class Validator {
    static void validate(String jsonSchema, String jsonInstance) {
        Schema schema = new Schema("$", parseObject(jsonSchema));
        schema.validations()
                .forEach(validation -> validation.validate(parse(jsonInstance)));
    }
}
