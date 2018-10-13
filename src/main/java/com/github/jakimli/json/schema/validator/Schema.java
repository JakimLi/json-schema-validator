package com.github.jakimli.json.schema.validator;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.type.Type.JsonSchema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.type.Type.typeOf;

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

        return typeOf((String) type)
                .schema(location, this.schema)
                .validations();
    }
}
