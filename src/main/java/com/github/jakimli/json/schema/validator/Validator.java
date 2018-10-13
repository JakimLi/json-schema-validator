package com.github.jakimli.json.schema.validator;

import com.github.jakimli.json.schema.validator.exception.ViolateJsonSchemaException;

import static com.alibaba.fastjson.JSON.parse;
import static com.alibaba.fastjson.JSON.parseObject;

class Validator {
    static void validate(String jsonSchema, String jsonInstance) {
        Schema schema = new Schema("$", parseObject(jsonSchema));

        boolean failure = schema.validations().stream()
                .map(validation -> validation.validate(parse(jsonInstance)))
                .anyMatch(passed -> !passed);

        if (failure) {
            throw new ViolateJsonSchemaException("violate json schema");
        }
    }
}
