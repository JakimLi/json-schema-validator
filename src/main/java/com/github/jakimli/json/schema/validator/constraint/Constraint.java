package com.github.jakimli.json.schema.validator.constraint;

import com.github.jakimli.json.schema.validator.InvalidException;

public interface Constraint {
    void apply(String instance) throws InvalidException;
}
