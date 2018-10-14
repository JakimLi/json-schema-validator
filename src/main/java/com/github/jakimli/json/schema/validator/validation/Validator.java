package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.CONST;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.ENUM;
import static com.google.common.collect.Lists.newArrayList;

public abstract class Validator implements JsonType.Validator {
    protected final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    protected Validator(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    @Override
    public List<Validation> validate() {
        add(ENUM.validate(this));
        add(CONST.validate(this));

        configure();
        return this.validations;
    }

    protected abstract void configure();

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
