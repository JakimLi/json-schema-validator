package com.github.jakimli.json.schema.validator.keyword;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.BadSchemaException;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class MultipleOfTest extends TestBase {


    @Test
    public void validate_multiple_of_fail_integer() throws Exception {
        String schema = readFile("keyword/multipleOf/integer.schema.json");
        String instance = readFile("keyword/multipleOf/11.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to be multiple of: 10, got: 11");
        validate(schema, instance);
    }

    @Test
    public void validate_multiple_of_success_integer() throws Exception {
        String schema = readFile("keyword/multipleOf/integer.schema.json");
        String instance = readFile("keyword/multipleOf/20.json");

        validate(schema, instance);
    }

    @Test
    public void validate_multiple_of_fail_decimal() throws Exception {
        String schema = readFile("keyword/multipleOf/decimal.schema.json");
        String instance = readFile("keyword/multipleOf/11.0.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to be multiple of: 10.0, got: 11.0");
        validate(schema, instance);
    }

    @Test
    public void validate_multiple_of_success_decimal() throws Exception {
        String schema = readFile("keyword/multipleOf/decimal.schema.json");
        String instance = readFile("keyword/multipleOf/30.0000.json");

        validate(schema, instance);
    }

    @Test
    public void validate_decimal_multiple_of_integer_fail() throws Exception {
        String schema = readFile("keyword/multipleOf/integer.schema.json");
        String instance = readFile("keyword/multipleOf/11.0.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to be multiple of: 10, got: 11.0");
        validate(schema, instance);
    }

    @Test
    public void validate_decimal_multiple_of_integer_success() throws Exception {
        String schema = readFile("keyword/multipleOf/integer.schema.json");
        String instance = readFile("keyword/multipleOf/30.0000.json");

        validate(schema, instance);
    }

    @Test
    public void validate_integer_multiple_of_decimal_fail() throws Exception {
        String schema = readFile("keyword/multipleOf/decimal.schema.json");
        String instance = readFile("keyword/multipleOf/11.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to be multiple of: 10.0, got: 11");
        validate(schema, instance);
    }

    @Test
    public void validate_integer_multiple_of_decimal_success() throws Exception {
        String schema = readFile("keyword/multipleOf/decimal.schema.json");
        String instance = readFile("keyword/multipleOf/20.json");

        validate(schema, instance);
    }

    @Test
    public void multiple_of_has_to_be_numeric() throws Exception {
        String schema = readFile("keyword/multipleOf/not_number.schema.json");
        String instance = readFile("keyword/multipleOf/20.json");

        exception.expect(BadSchemaException.class);
        exception.expectMessage("multipleOf must to be numeric, got: string");
        validate(schema, instance);
    }

    @Test
    public void multiple_of_has_to_be_greater_than_0() throws Exception {
        String schema = readFile("keyword/multipleOf/less0.schema.json");
        String instance = readFile("keyword/multipleOf/20.json");

        exception.expect(BadSchemaException.class);
        exception.expectMessage("multipleOf must to be greater than 0, got: -10.0");
        validate(schema, instance);
    }
}
