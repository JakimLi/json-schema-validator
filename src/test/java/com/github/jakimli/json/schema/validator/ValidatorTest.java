package com.github.jakimli.json.schema.validator;

import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class ValidatorTest extends TestBase {

    @Test
    public void always_valid() throws Exception {
        validate("{}", "whatever");
        validate("{}", "{}");
        validate("{}", "{\"a\": 13}");
        validate("{}", "13");

        validate("true", "whatever");
        validate("true", "{}");
        validate("true", "{\"a\": 13}");
        validate("true", "13");

        validate(readFile("true.json"), "whatever");
        validate(readFile("true.json"), "{}");
        validate(readFile("true.json"), "{\"a\": 13}");
        validate(readFile("true.json"), "13");
    }

    @Test
    public void always_violated() throws Exception {
        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("always fail");
        validate("false", "whatever");
    }

    @Test
    public void should_validate_invalid_object() throws Exception {
        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [INTEGER], got: not integer");

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
