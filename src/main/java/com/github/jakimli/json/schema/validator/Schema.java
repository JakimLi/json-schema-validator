package com.github.jakimli.json.schema.validator;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import com.github.jakimli.json.schema.validator.keywords.Keywords;
import com.github.jakimli.json.schema.validator.keywords.Type;
import com.github.jakimli.json.schema.validator.type.JsonType;
import com.github.jakimli.json.schema.validator.type.JsonType.JsonSchema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.JSON.parse;
import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;
import static com.google.common.collect.Lists.newArrayList;

public class Schema implements JsonSchema {
    private Object schema;

    private String location;

    public Schema(String location, Object schema) {
        this.location = location;
        this.schema = schema;
    }

    @Override
    public List<Validation> validations() {
        List<Validation> validations = newArrayList();

        if (alwaysTrue(schema)) {
            return validations;
        }

        if (alwaysFalse(schema)) {
            throw new SchemaViolatedException("always fail");
        }

        if (!(schema instanceof JSONObject)) {
            throw invalidSchema("invalid schema");
        }

        JSONObject schema = (JSONObject) this.schema;
        Object type = schema.get("type");

        Type keyword = (Type) Keywords.byKeyword("type").get();
        validations.addAll(keyword.validations(this.location, type));
        validations.addAll(byType(keyword.types(type), schema));

        return validations;
    }

    private List<Validation> byType(List<JsonType> types, JSONObject schema) {
        return types.stream()
                .map(t -> t.schema(location, schema))
                .map(JsonSchema::validations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static boolean alwaysTrue(Object schema) {
        return (parse("{}").equals(schema) || parse("true").equals(schema));
    }


    private static boolean alwaysFalse(Object schema) {
        return parse("false").equals(schema);
    }

}
