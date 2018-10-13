package com.github.jakimli.json.schema.validator;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.exception.ViolateJsonSchemaException;
import com.github.jakimli.json.schema.validator.type.Type;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.type.Type.fromKeyword;

class Schema implements Type.Schema {
    private JSONObject schema;

    private String location;

    Schema(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    @Override
    public List<Validation> validations() {
        Object type = this.schema.get("type");

        return fromKeyword((String) type)
                .schema(location, this.schema)
                .validations();
    }

    void validate(Object instance) {
        boolean failure = validations().stream()
                .map(validation -> validation.validate(instance))
                .anyMatch(passed -> !passed);

        if (failure) {
            throw new ViolateJsonSchemaException("violate json schema");
        }
    }

}
