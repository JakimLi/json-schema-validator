package com.github.jakimli.json.schema.validator.keyword.mumber;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class ExclusiveMinimumTest extends TestBase {


    @Test
    public void integer_exclusive_min_fail() throws Exception {
        String schema = readFile("keyword/exclusiveMinimum/decimal.schema.json");
        String instance = readFile("keyword/exclusiveMinimum/9.111111.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to greater than: 10.0, got: 9.111111");
        validate(schema, instance);
    }

    @Test
    public void integer_exclusive_min_equals_fail() throws Exception {
        String schema = readFile("keyword/exclusiveMinimum/decimal.schema.json");
        String instance = readFile("keyword/exclusiveMinimum/10.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to greater than: 10.0, got: 10");
        validate(schema, instance);
    }
    
    @Test
    public void integer_exclusive_min_less_success() throws Exception {
        String schema = readFile("keyword/exclusiveMinimum/decimal.schema.json");
        String instance = readFile("keyword/exclusiveMinimum/100.3333333.json");

        validate(schema, instance);
    }
}
