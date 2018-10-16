package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.github.jakimli.json.schema.validator.validation.Type.type;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class Schema implements JsonType.Validator {

    private final String location;
    private final JSONObject schema;

    public Schema(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
    }

    @Override
    public List<Validation> validate() {
        List<Validation> validations = newArrayList();
        validations.addAll(type(this).validate());
        validations.addAll(subSchema());
        return validations;
    }

    private List<Validation> subSchema() {
        return Arrays.stream(JsonType.values())
                .map(t -> t.validator(this))
                .map(JsonType.Validator::validate)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    Object get(String word) {
        return schema.get(word);
    }

    String location() {
        return this.location;
    }
}
