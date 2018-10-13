package com.github.jakimli.json.schema.validator;

import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;

public class SchemaValidatorValidatorTest {

    @Test
    public void no_constraints() {
        validate("{}", "{\"any\": \"json\"}");
    }
}
