package com.github.jakimli.json.schema.validator.constraint;

import com.github.jakimli.json.schema.validator.assertion.Assertion;
import com.github.jakimli.json.schema.validator.exception.InvalidException;

import static com.alibaba.fastjson.JSON.parseObject;
import static com.alibaba.fastjson.JSONPath.compile;

public class Constraint {
    String location;
    private Assertion assertion;

    public Constraint(String location, Assertion assertion) {
        this.location = location;
        this.assertion = assertion;
    }

    public void apply(String instance) throws InvalidException {
        if (!assertion.asserts(subInstance(instance))) {
            throw new InvalidException(assertion.message());
        }
    }

    private Object subInstance(String instance) {
        return compile(location).eval(parseObject(instance));
    }
}
