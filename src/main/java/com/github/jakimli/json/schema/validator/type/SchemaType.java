package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.ENUM;
import static com.google.common.collect.Lists.newArrayList;

public abstract class SchemaType implements JsonType.JsonSchema {
    final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    SchemaType(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    @Override
    public List<Validation> validations() {
        add(ENUM.validations(this));

        configure();
        return this.validations;
    }

    abstract void configure();

    void add(Validation... validations) {
        this.validations.addAll(newArrayList(validations));
    }

    void add(List<Validation> validations) {
        if (validations == null) {
            return;
        }
        this.validations.addAll(validations);
    }

    public String location() {
        return location;
    }

    public Object sub(String word) {
        return this.schema.get(word);
    }
}
