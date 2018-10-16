package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.keywords.Keywords;

import java.util.List;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.CONST;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.ENUM;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.TYPE;
import static com.google.common.collect.Lists.newArrayList;

public class Type implements JsonType.Validator {
    final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    Type(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    private Type(Schema schema) {
        this.location = schema.location;
        this.schema = schema.schema;
    }

    public static Type type(Schema schema) {
        return new Type(schema);
    }

    @Override
    public List<Validation> validate() {
        keywords();
        return this.validations;
    }

    protected void keywords() {
        keyword(ENUM);
        keyword(CONST);
        keyword(TYPE);
    };

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

    protected Type keyword(Keywords keyword) {
        add(keyword.validate(this));
        return this;
    }
}
