package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.TYPE;

public class Schema extends Validator implements JsonType.Validator {
    public Schema(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
        add(TYPE.validate(this));
        add(subSchema());
    }

    private List<Validation> subSchema() {
        return Arrays.stream(JsonType.values())
                .map(t -> t.validator(this))
                .map(JsonType.Validator::validate)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
