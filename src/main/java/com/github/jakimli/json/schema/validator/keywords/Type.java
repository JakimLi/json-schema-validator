package com.github.jakimli.json.schema.validator.keywords;

import com.alibaba.fastjson.JSONArray;
import com.github.jakimli.json.schema.validator.predicates.Predicates;
import com.github.jakimli.json.schema.validator.type.JsonType;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.assertion.Assertion.AssertionBuilder.expect;
import static com.github.jakimli.json.schema.validator.assertion.Assertions.anyOfTypes;
import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;
import static com.github.jakimli.json.schema.validator.type.JsonType.typeOf;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public class Type implements Keyword<Object> {

    @Override
    public List<Validation> validations(String location, Object value) {
        return newArrayList(assertion(anyOfTypes(types(value))).at(location));
    }

    public List<JsonType> types(Object value) {
        List<JsonType> types = newArrayList();

        if (value instanceof String) {
            types.add(typeOf((String) value));
        }

        if (value instanceof JSONArray) {
            List<String> declaredTypes = ((JSONArray) value).toJavaList(String.class);
            assertUnique(declaredTypes);
            assertNotEmpty(declaredTypes);
            types.addAll(declaredTypes.stream().map(JsonType::typeOf).collect(Collectors.toList()));
        }

        return types;
    }

    private void assertNotEmpty(List<String> declaredTypes) {
        expect((Predicate<List<String>>) strings -> strings.size() > 0)
                .test(declaredTypes, invalidSchema("type must be a string or a non-empty array"));
    }

    private void assertUnique(List<String> declaredTypes) {
        expect(Predicates.<String>unique())
                .test(declaredTypes, invalidSchema("types must be unique"));
    }
}
