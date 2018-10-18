package com.github.jakimli.json.schema.validator.keyword.mumber;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class ExclusiveMaximumTest extends TestBase {


    @Test
    public void integer_exclusive_max_fail() throws Exception {
        String schema = readFile("keyword/exclusiveMaximum/integer.schema.json");
        String instance = readFile("keyword/exclusiveMaximum/11.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to less than: 10, got: 11");
        validate(schema, instance);
    }

    @Test
    public void integer_exclusive_max_equals_fail() throws Exception {
        String schema = readFile("keyword/exclusiveMaximum/integer.schema.json");
        String instance = readFile("keyword/exclusiveMaximum/10.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to less than: 10, got: 10");
        validate(schema, instance);
    }
    
    @Test
    public void integer_exclusive_max_less_success() throws Exception {
        String schema = readFile("keyword/exclusiveMaximum/integer.schema.json");
        String instance = readFile("keyword/exclusiveMaximum/0.json");

        validate(schema, instance);
    }
}
