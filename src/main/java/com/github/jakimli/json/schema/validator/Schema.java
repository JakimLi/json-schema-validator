package com.github.jakimli.json.schema.validator;

import java.util.ArrayList;
import java.util.List;

import static com.github.jakimli.json.schema.validator.Rules.rulesOf;

class Schema {
    private List<Rule> rules = new ArrayList<>();

    static void validate(String schema, String instance) {
        rulesOf(schema).forEach(rule -> rule.apply(instance));
    }
}
