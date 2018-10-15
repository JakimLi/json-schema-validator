package com.github.jakimli.json.schema.validator.validation;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.PROPERTIES;

class ObjectType extends Validator {

    ObjectType(Schema schema) {
        super(schema);
    }

    @Override
    protected void configure() {
        add(PROPERTIES.validate(this));
    }
}
