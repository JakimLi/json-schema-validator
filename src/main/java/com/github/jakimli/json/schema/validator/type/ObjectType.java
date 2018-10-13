package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.Schema;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.objectType;
import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;

class ObjectType extends AbstractType {

    ObjectType(String location, JSONObject schema) {
        super(location, schema);
    }

    @Override
    void configure() {
        add(validation(objectType()));
        properties();
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

        add(keys.stream()
                .map(key -> schema(key, propertiesSchema))
                .map(Schema::validations)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }

    private Schema schema(String key, JSONObject schema) {
        return new Schema(this.location + "." + key, (JSONObject) schema.get(key));
    }
}
