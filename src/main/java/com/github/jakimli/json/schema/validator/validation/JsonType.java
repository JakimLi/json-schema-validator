package com.github.jakimli.json.schema.validator.validation;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import static com.github.jakimli.json.schema.validator.exception.InvalidKeywordException.invalidKeywordException;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfArray;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfBigDecimal;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfBoolean;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfInteger;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfJSONObject;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.instanceOfString;
import static com.github.jakimli.json.schema.validator.predicates.Predicates.or;
import static com.github.jakimli.json.schema.validator.validation.Types.arrayType;
import static com.github.jakimli.json.schema.validator.validation.Types.booleanType;
import static com.github.jakimli.json.schema.validator.validation.Types.integerType;
import static com.github.jakimli.json.schema.validator.validation.Types.nullType;
import static com.github.jakimli.json.schema.validator.validation.Types.numberType;
import static com.github.jakimli.json.schema.validator.validation.Types.objectType;
import static com.github.jakimli.json.schema.validator.validation.Types.stringType;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public enum JsonType {

    OBJECT("object", objectType()),
    NULL("null", nullType()),
    BOOLEAN("boolean", booleanType()),
    INTEGER("integer", integerType()),
    NUMBER("number", numberType()),
    ARRAY("array", arrayType()),
    STRING("string", stringType());

    private String keyword;
    private Factory factory;

    JsonType(String keyword, Factory factory) {
        this.keyword = keyword;
        this.factory = factory;
    }

    public static JsonType typeOf(String keyword) {
        return stream(JsonType.values())
                .filter(type -> type.keyword.equals(keyword))
                .findFirst()
                .orElseThrow(() -> invalidKeywordException(keyword));
    }

    public static List<Predicate<Object>> predicates(List<JsonType> types) {
        return types.stream().map(JsonType::predicate).collect(toList());
    }

    public Predicate<Object> predicate() {
        return ((Map<JsonType, Predicate<Object>>) ImmutableMap.<JsonType, Predicate<Object>>builder()
                .put(ARRAY, instanceOfArray())
                .put(STRING, instanceOfString())
                .put(INTEGER, instanceOfInteger())
                .put(NULL, Objects::isNull)
                .put(OBJECT, instanceOfJSONObject())
                .put(BOOLEAN, instanceOfBoolean())
                .put(NUMBER, or(instanceOfBigDecimal(), instanceOfInteger()))
                .build())
                .get(this);
    }

    public Validator validator(Schema schema) {
        return this.factory.create(schema);
    }

    interface Factory {
        Validator create(Schema schema);
    }

    public interface Validator {
        List<Validation> validate();
    }
}
