package com.github.jakimli.json.schema.validator;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.type.Type.JsonSchema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.type.Type.fromKeyword;

public class Schema implements JsonSchema {
    private JSONObject schema;

    private String location;

    public Schema(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    @Override
    public List<Validation> validations() {
        Object type = this.schema.get("type");

        return fromKeyword((String) type)
                .collector(location, this.schema)
                .validations();
    }
}
