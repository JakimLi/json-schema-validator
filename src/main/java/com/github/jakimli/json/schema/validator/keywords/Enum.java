package com.github.jakimli.json.schema.validator.keywords;

import com.alibaba.fastjson.JSONArray;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.arrayType;
import static com.github.jakimli.json.schema.validator.assertion.Assertions.oneOf;
import static com.github.jakimli.json.schema.validator.exception.InvalidSchemaException.invalidSchema;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public class Enum implements Keyword {

    @Override
    public List<Validation> validations(String location, Object value) {

        arrayType(i -> invalidSchema("expected type array", i)).asserts(value);

        List<Object> enums = ((JSONArray) value).toJavaList(Object.class);
        return newArrayList(assertion(oneOf(enums)).at(location));
    }
}
