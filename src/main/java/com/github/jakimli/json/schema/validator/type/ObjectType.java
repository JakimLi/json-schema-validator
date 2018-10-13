package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.assertion.ObjectTypeAssertion;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ObjectType implements Type.Validator {

    private final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    private ObjectType(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
        validations.add(new Validation(this.location, new ObjectTypeAssertion()));
    }

    ObjectType(JSONObject schema) {
        this("$", schema);
    }

    @Override
    public List<Validation> validations() {
        return validations;
    }
}
