package com.github.jakimli.json.schema.validator.constraint;

import com.github.jakimli.json.schema.validator.assertion.NullAssertion;

import java.util.List;
import java.util.Map;

import static com.github.jakimli.json.schema.validator.util.JsonUtil.toMap;
import static com.google.common.collect.Lists.newArrayList;

public class Constraints {

    public static List<Constraint> constraints(String jsonSchema) {
        Map<String, Object> schema = toMap(jsonSchema);

        Object type = schema.get("type");
        if (type != null && type.equals("null")) {
            Constraint constraint = new Constraint("$");
            constraint.assertion(new NullAssertion());
            return newArrayList(constraint);
        }

        return newArrayList();
    }
}
