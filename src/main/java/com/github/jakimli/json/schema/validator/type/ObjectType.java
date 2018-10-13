package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.type.Type.Schema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.github.jakimli.json.schema.validator.assertion.Assertions.objectType;
import static com.github.jakimli.json.schema.validator.assertion.Assertions.properties;
import static com.google.common.collect.Lists.newArrayList;

public class ObjectType implements Schema {

    private final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    ObjectType(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
        validations.add(assertion(objectType()).at(location));
    }

    @Override
    public List<Validation> validations() {
        Object properties = schema.get("properties");
        if (properties != null) {
            validations.add(assertion(properties(properties)).at(location));
        }
        return validations;
    }
}
