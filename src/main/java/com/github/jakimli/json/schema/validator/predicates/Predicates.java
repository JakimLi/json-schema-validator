package com.github.jakimli.json.schema.validator.predicates;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import static java.math.RoundingMode.UNNECESSARY;
import static java.util.Arrays.stream;

public class Predicates {


    public static Predicate<Integer> multipleOf(Integer integer) {
        return t -> t % integer == 0;
    }

    public static Predicate<BigDecimal> multipleOf(BigDecimal decimal) {
        return t -> t.divide(decimal, UNNECESSARY).stripTrailingZeros().scale() <= 0;
    }

    public static <T> Predicate<T> oneOf(List<T> objects) {
        return t -> objects.stream().anyMatch(o -> o.equals(t));
    }

    public static <T> Predicate<List<T>> unique() {
        return t -> new HashSet<>(t).size() == t.size();
    }

    public static Predicate<Object> instanceOfJSONObject() {
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
}
