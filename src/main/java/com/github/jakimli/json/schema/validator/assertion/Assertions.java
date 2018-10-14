package com.github.jakimli.json.schema.validator.assertion;

import com.github.jakimli.json.schema.validator.predicates.Predicates;
import com.github.jakimli.json.schema.validator.type.JsonType;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static com.github.jakimli.json.schema.validator.assertion.Assertion.AssertionBuilder.expect;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfArray;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfBigDecimal;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfBoolean;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfInteger;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfObject;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfString;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.or;
import static java.util.stream.Collectors.toList;

public class Assertions {

    public static Assertion<Object> objectType() {
        return expect(instanceOfObject()).message("expected type object")::test;
    }

    public static Assertion<Object> stringType() {
        return expect(instanceOfString()).message("expected type string")::test;
    }

    public static Assertion<Object> integerType() {
        return expect(instanceOfInteger()).message("expected type integer")::test;
    }

    public static Assertion<Object> numberType() {
        return expect(instanceOfBigDecimal()).message("expected type number")::test;
    }

    public static Assertion<Object> arrayType() {
        return expect(instanceOfArray()).message("expected type array")::test;
    }

    public static Assertion<Object> nullType() {
        return expect(Objects::isNull).message("expected null")::test;
    }

    public static Assertion<Object> booleanType() {
        return expect(instanceOfBoolean()).message("expected type boolean")::test;
    }

    public static Assertion<Object> oneOfTypes(List<JsonType> types) {
        return expect(or(predicates(types))).message("expected one of type: " + types)::test;
    }

    private static List<Predicate<Object>> predicates(List<JsonType> types) {
        return types.stream().map(Predicates::byType).collect(toList());
    }

}
