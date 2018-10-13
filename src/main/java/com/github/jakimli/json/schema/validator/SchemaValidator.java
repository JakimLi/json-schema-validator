package com.github.jakimli.json.schema.validator;

import static com.github.jakimli.json.schema.validator.constraint.Constraints.constraints;

class SchemaValidator {

    static void validate(String schema, String instance) {
        constraints(schema).forEach(constraint -> constraint.apply(instance));
    }
}
