package com.github.jakimli.json.schema.validator;

import com.github.jakimli.json.schema.validator.exception.ViolateJsonSchemaException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.jakimli.json.schema.validator.Validator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class ValidatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_validate_invalid_object() throws Exception {
        exception.expect(ViolateJsonSchemaException.class);
        exception.expectMessage("expected type integer, got: not integer");

        String schema = readFile("product.schema.json");
        String instance = readFile("invalid.product.json");

        validate(schema, instance);
    }

    @Test
    public void should_validate_valid_object() throws Exception {
        String schema = readFile("product.schema.json");
        String instance = readFile("product.json");

        validate(schema, instance);
    }
}
