package com.github.jakimli.json.schema.validator.keywords;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.validation.Schema;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.badSchema;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfJSONObject;
import static java.util.stream.Collectors.toList;

public class Properties implements Keyword {
    @Override
    public List<Validation> validations(String location, Object properties) {

        if (!instanceOfJSONObject().test(properties)) {
            throw badSchema("properties must be json object: " + properties);
        }

        return ((JSONObject) properties).entrySet().stream()
                .map(entry -> schema(location, entry))
                .map(Schema::validate)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private Schema schema(String location, Map.Entry<String, Object> entry) {
        return new Schema(location + "." + entry.getKey(), (JSONObject) entry.getValue());
    }
}
