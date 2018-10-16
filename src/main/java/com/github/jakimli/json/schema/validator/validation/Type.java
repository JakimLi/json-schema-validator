package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.keywords.Keywords;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.CONST;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.ENUM;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.TYPE;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Stream.concat;

public class Type implements JsonType.Validator {
    final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    private List<Keywords> keywords = newArrayList(ENUM, CONST, TYPE);

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
        return concat(this.validations.stream(),
                validateEachKeywords().stream())
                .collect(Collectors.toList());
    }

    private List<Validation> validateEachKeywords() {
        return this.keywords.stream()
                .map(k -> k.validate(this))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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

    Type keyword(Keywords keyword) {
        this.keywords.add(keyword);
        return this;
    }
}
