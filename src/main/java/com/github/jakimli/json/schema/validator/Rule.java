package com.github.jakimli.json.schema.validator;

interface Rule {
    void apply(String instance) throws InvalidException;
}
