package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.assertion.Assertion;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public abstract class SchemaType implements Type.JsonSchema {
    final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    SchemaType(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    @Override
    public List<Validation> validations() {
        configure();
        return this.validations;
    }

    abstract void configure();

    void add(Validation... validations) {
        this.validations.addAll(newArrayList(validations));
    }

    void add(List<Validation> validations) {
        this.validations.addAll(validations);
    }

    Validation validation(Assertion assertion) {
        return assertion(assertion).at(location);
    }
}
