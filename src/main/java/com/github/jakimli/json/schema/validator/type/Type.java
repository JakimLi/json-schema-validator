package com.github.jakimli.json.schema.validator.type;

import com.github.jakimli.json.schema.validator.constraint.Constraint;

import java.util.List;
import java.util.Map;

public interface Type {

    boolean isType(Map<String, Object> object);

    List<Constraint> constraints(Map<String, Object> schema);
}
