package com.github.jakimli.json.schema.validator;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.keywords.Type;
import com.github.jakimli.json.schema.validator.type.JsonType;
import com.github.jakimli.json.schema.validator.type.JsonType.JsonSchema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.byKeyword;
import static com.google.common.collect.Lists.newArrayList;

public class Schema implements JsonSchema {
    private JSONObject schema;

    private String location;

    public Schema(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;

        if (schema == null) {
            throw invalidSchema("invalid schema");
        }
    }

    @Override
    public List<Validation> validations() {
        List<Validation> validations = newArrayList();

        Object type = this.schema.get("type");

        Type keyword = (Type) byKeyword("type").get();
        validations.addAll(keyword.validations(this.location, type));
        validations.addAll(byType(keyword.types(type), this.schema));

        return validations;
    }

    private List<Validation> byType(List<JsonType> types, JSONObject schema) {
        return types.stream()
                .map(t -> t.schema(location, schema))
                .map(JsonSchema::validations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


}
