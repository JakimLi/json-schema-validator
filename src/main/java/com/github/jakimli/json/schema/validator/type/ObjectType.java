package com.github.jakimli.json.schema.validator.type;

import com.github.jakimli.json.schema.validator.assertion.ObjectTypeAssertion;
import com.github.jakimli.json.schema.validator.constraint.Constraint;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

public class ObjectType implements Type {

    @Override
    public boolean isType(Map<String, Object> object) {
        Object type = object.get("type");
        return type != null && type.equals("object");
    }

    @Override
    public List<Constraint> constraints(Map<String, Object> schema) {
        return newArrayList(new Constraint("$", new ObjectTypeAssertion()));
    }
}
