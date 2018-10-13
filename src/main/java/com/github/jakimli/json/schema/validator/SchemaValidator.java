package com.github.jakimli.json.schema.validator;

import com.github.jakimli.json.schema.validator.constraint.Constraint;

import java.util.ArrayList;
import java.util.List;

import static com.github.jakimli.json.schema.validator.constraint.Constraints.constraints;

class SchemaValidator {
    private List<Constraint> constraints = new ArrayList<>();

    static void validate(String schema, String instance) {
        constraints(schema).forEach(constraint -> constraint.apply(instance));
    }
}
