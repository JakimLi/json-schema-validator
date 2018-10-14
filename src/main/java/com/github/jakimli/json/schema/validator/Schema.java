package com.github.jakimli.json.schema.validator;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.keywords.Type;
import com.github.jakimli.json.schema.validator.type.JsonType;
import com.github.jakimli.json.schema.validator.type.JsonType.JsonSchema;
import com.github.jakimli.json.schema.validator.type.SchemaType;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.TYPE;
import static com.github.jakimli.json.schema.validator.keywords.Keywords.byKeyword;

public class Schema extends SchemaType implements JsonSchema {
    public Schema(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    protected void configure() {
        Object type = this.schema.get("type");

        add(TYPE.validations(this));

        add(byType(((Type) byKeyword("type").get()).types(type), this.schema));
    }

    private List<Validation> byType(List<JsonType> types, JSONObject schema) {
        return types.stream()
                .map(t -> t.schema(location, schema))
                .map(JsonSchema::validations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
