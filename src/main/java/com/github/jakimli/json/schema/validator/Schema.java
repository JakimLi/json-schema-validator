package com.github.jakimli.json.schema.validator;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.exception.ViolateJsonSchemaException;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.type.Type.fromKeyword;
import static com.google.common.collect.Lists.newArrayList;

class Schema {
    private JSONObject schema;

    private List<Validation> validations = newArrayList();

    Schema(JSONObject schema) {
        this.schema = schema;

        Object type = this.schema.get("type");
        validations.addAll(
                fromKeyword((String) type)
                        .type(this.schema)
                        .validations()
        );
    }

    void validate(Object instance) {
        boolean failure = validations.stream()
                .map(validation -> validation.validate(instance))
                .anyMatch(passed -> !passed);

        if (failure) {
            throw new ViolateJsonSchemaException("violate json schema");
        }
    }

}
