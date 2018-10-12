package com.github.jakimli.json.schema.validator;

import org.junit.Test;

import static com.github.jakimli.json.schema.validator.Schema.validate;

public class SchemaTest {

    @Test
    public void no_constraints() {
        validate("{}", "{\"any\": \"json\"}");
    }
}
