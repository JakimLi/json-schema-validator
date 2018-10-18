package com.github.jakimli.json.schema.validator.keywords;

import com.alibaba.fastjson.JSONArray;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.assertion.Assertions.oneOf;
import static com.github.jakimli.json.schema.validator.exception.BadSchemaException.badSchema;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfArray;
import static com.github.jakimli.json.schema.validator.validation.Validation.Builder.assertion;
import static com.google.common.collect.Lists.newArrayList;

public class Enum implements Keyword.Validator {

    @Override
    public List<Validation> validate(String location, Object value) {
        if (!instanceOfArray().test(value)) {
            throw badSchema("expected type array", value);
        }

        List<Object> enums = ((JSONArray) value).toJavaList(Object.class);
        return newArrayList(assertion(oneOf(enums)).at(location));
    }
}
