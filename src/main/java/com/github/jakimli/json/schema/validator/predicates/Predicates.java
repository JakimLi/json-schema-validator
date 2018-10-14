package com.github.jakimli.json.schema.validator.predicates;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.type.JsonType;
import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static com.github.jakimli.json.schema.validator.type.JsonType.ARRAY;
import static com.github.jakimli.json.schema.validator.type.JsonType.BOOLEAN;
import static com.github.jakimli.json.schema.validator.type.JsonType.INTEGER;
import static com.github.jakimli.json.schema.validator.type.JsonType.NULL;
import static com.github.jakimli.json.schema.validator.type.JsonType.NUMBER;
import static com.github.jakimli.json.schema.validator.type.JsonType.OBJECT;
import static com.github.jakimli.json.schema.validator.type.JsonType.STRING;
import static java.util.Arrays.stream;

public class Predicates {

    public static <T> Predicate<List<T>> unique() {
        return t -> new HashSet<>(t).size() == t.size();
    }

    public static Predicate<Object> instanceOfObject() {
        return it -> it instanceof JSONObject;
    }

    public static Predicate<Object> instanceOfString() {
        return it -> it instanceof String;
    }

    public static Predicate<Object> instanceOfInteger() {
        return it -> it instanceof Integer;
    }

    public static Predicate<Object> instanceOfBigDecimal() {
        return it -> it instanceof BigDecimal;
    }

    public static Predicate<Object> instanceOfArray() {
        return it -> it instanceof JSONArray;
    }

    public static Predicate<Object> instanceOfBoolean() {
        return it -> it instanceof Boolean;
    }

    @SafeVarargs
    public static <T> Predicate<T> or(Predicate<T>... predicates) {
        return it -> stream(predicates).anyMatch(predicate -> predicate.test(it));
    }

    public static <T> Predicate<T> or(List<Predicate<T>> predicates) {
        return it -> predicates.stream().anyMatch(predicate -> predicate.test(it));
    }

    public static Predicate<Object> byType(JsonType type) {
        ImmutableMap<JsonType, Predicate<Object>> predicates = ImmutableMap.<JsonType, Predicate<Object>>builder()
                .put(ARRAY, instanceOfArray())
                .put(STRING, instanceOfString())
                .put(INTEGER, instanceOfInteger())
                .put(NULL, Objects::isNull)
                .put(OBJECT, instanceOfObject())
                .put(BOOLEAN, instanceOfBoolean())
                .put(NUMBER, instanceOfBigDecimal())
                .build();
        return predicates.get(type);
    }
}
