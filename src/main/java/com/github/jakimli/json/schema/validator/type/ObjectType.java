package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.Schema;
import com.github.jakimli.json.schema.validator.type.Type.JsonSchema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.objectType;
import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public class ObjectType implements JsonSchema {

    private final String location;
    protected final JSONObject schema;

    private List<Validation> validations = newArrayList();

    ObjectType(String location, JSONObject schema) {
        this.location = location;
        this.schema = schema;
        validations.add(assertion(objectType()).at(location));
    }

    @Override
    public List<Validation> validations() {
        properties();
        return validations;
    }

    private void properties() {
        Object properties = schema.get("properties");

        if (properties == null) {
            return;
        }

        if (!(properties instanceof JSONObject)) {
            throw invalidSchema("properties must be json object: " + properties);
        }

        JSONObject propertiesSchema = (JSONObject) properties;
        Set<String> keys = propertiesSchema.keySet();

        this.validations.addAll(keys.stream()
                .map(key -> schema(key, propertiesSchema))
                .map(Schema::validations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }

    private Schema schema(String key, JSONObject schema) {
        return new Schema(this.location + "." + key, (JSONObject) schema.get(key));
    }
}
