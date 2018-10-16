package com.github.jakimli.json.schema.validator.assertion;

import com.github.jakimli.json.schema.validator.predicates.Predicates;
import com.github.jakimli.json.schema.validator.validation.JsonType;

import java.util.List;

import static com.github.jakimli.json.schema.validator.assertion.Assertion.AssertionBuilder.expect;
import static com.github.jakimli.json.schema.validator.exception.SchemaViolatedException.violated;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.or;

public class Assertions {

    public static Assertion<Object> oneOf(List<Object> objects) {
        return expect(Predicates.oneOf(objects))
                .toThrow((i) -> violated("must be one of values in enum: " + objects, i))::test;
    }

    public static Assertion<Object> anyOfTypes(List<JsonType> types) {
        return expect(or(JsonType.predicates(types)))
                .toThrow((i) -> violated("expected one of type: " + types, i))::test;
    }

    public static Assertion<Object> mustBe(Object object) {
        return expect(object::equals).toThrow(i -> violated("must be: " + object, i))::test;
    }

}
