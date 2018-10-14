package com.github.jakimli.json.schema.validator.assertion;

import com.github.jakimli.json.schema.validator.predicates.Predicates;
import com.github.jakimli.json.schema.validator.validation.JsonType;

import java.util.List;
import java.util.function.Predicate;

import static com.github.jakimli.json.schema.validator.assertion.Assertion.AssertionBuilder.expect;
import static com.github.jakimli.json.schema.validator.exception.SchemaViolatedException.violated;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfArray;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.or;
import static java.util.stream.Collectors.toList;

public class Assertions {

    public static Assertion<Object> arrayType(Assertion.ExceptionSupplier exception) {
        return expect(instanceOfArray()).toThrow(exception)::test;
    }

    public static Assertion<Object> oneOf(List<Object> objects) {
        return expect(Predicates.oneOf(objects))
                .toThrow((i) -> violated("must be one of values in enum: " + objects, i))::test;
    }

    public static Assertion<Object> anyOfTypes(List<JsonType> types) {
        return expect(or(predicates(types)))
                .toThrow((i) -> violated("expected one of type: " + types, i))::test;
    }

    public static Assertion<Object> mustBe(Object object) {
        return expect(object::equals).toThrow(i -> violated("must be: " + object, i))::test;
    }

    private static List<Predicate<Object>> predicates(List<JsonType> types) {
        return types.stream().map(Predicates::byType).collect(toList());
    }

}
