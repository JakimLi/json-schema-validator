package com.github.jakimli.json.schema.validator.constraint;

import com.github.jakimli.json.schema.validator.InvalidException;
import com.github.jakimli.json.schema.validator.assertion.Assertion;

import static com.alibaba.fastjson.JSON.parseObject;
import static com.alibaba.fastjson.JSONPath.compile;

public class Constraint {
    String location;
    private Assertion assertion;

    Constraint(String location) {
        this.location = location;
    }

    void assertion(Assertion assertion) {
        this.assertion = assertion;
    }

    public void apply(String instance) throws InvalidException {
        if (!assertion.asserts(subInstance(instance))) {
            throw new InvalidException(assertion);
        }
    }

    private Object subInstance(String instance) {
        return compile(location).eval(parseObject(instance));
    }
}
