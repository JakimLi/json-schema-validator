package com.github.jakimli.json.schema.validator.keywords;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.validation.Schema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;

public class Properties implements Keyword {
    @Override
    public List<Validation> validations(String location, Object properties) {

        if (!(properties instanceof JSONObject)) {
            throw invalidSchema("properties must be json object: " + properties);
        }

        JSONObject propertiesSchema = (JSONObject) properties;
        Set<String> keys = propertiesSchema.keySet();

        return keys.stream()
                .map(key -> schema(key, propertiesSchema, location))
                .map(Schema::validate)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Schema schema(String key, JSONObject schema, String location) {
        return new Schema(location + "." + key, (JSONObject) schema.get(key));
    }
}
