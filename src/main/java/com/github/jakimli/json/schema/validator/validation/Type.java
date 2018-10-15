package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.keywords.Keywords;

import java.util.List;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.CONST;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.ENUM;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.TYPE;
import static com.google.common.collect.Lists.newArrayList;

public abstract class Type implements JsonType.Validator {
    final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    protected Type(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    protected Type(Schema schema) {
        this.location = schema.location;
        this.schema = schema.schema;
    }

    @Override
    public List<Validation> validate() {
        keyword(ENUM);
        keyword(CONST);
        keyword(TYPE);

        keywords();
        return this.validations;
    }

    protected abstract void keywords();

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

    protected void keyword(Keywords keyword) {
        add(keyword.validate(this));
    }
}
