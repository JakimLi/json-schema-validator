package com.github.jakimli.json.schema.validator.keyword.mumber;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.BadSchemaException;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class MaximumTest extends TestBase {

    @Test
    public void integer_max_but_greater_than() throws Exception {
        String schema = readFile("keyword/maximum/integer.schema.json");
        String instance = readFile("keyword/maximum/11.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to less than or equals to: 10, got: 11");
        validate(schema, instance);
    }

    @Test
    public void integer_max_equal() throws Exception {
        String schema = readFile("keyword/maximum/integer.schema.json");
        String instance = readFile("keyword/maximum/10.json");

        validate(schema, instance);
    }
    
    @Test
    public void integer_max_less_than() throws Exception {
        String schema = readFile("keyword/maximum/integer.schema.json");
        String instance = readFile("keyword/maximum/9.json");

        validate(schema, instance);
    }

    @Test
    public void decimal_max_but_greater_than() throws Exception {
        String schema = readFile("keyword/maximum/decimal.schema.json");
        String instance = readFile("keyword/maximum/11.05.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to less than or equals to: 10.05, got: 11.05");
        validate(schema, instance);
    }

    @Test
    public void decimal_max_equal() throws Exception {
        String schema = readFile("keyword/maximum/decimal.schema.json");
        String instance = readFile("keyword/maximum/10.05.json");

        validate(schema, instance);
    }

    @Test
    public void decimal_max_less_than() throws Exception {
        String schema = readFile("keyword/maximum/decimal.schema.json");
        String instance = readFile("keyword/maximum/9.05.json");

        validate(schema, instance);
    }

    @Test
    public void decimal_max_but_greater_than_by_integer() throws Exception {
        String schema = readFile("keyword/maximum/decimal.schema.json");
        String instance = readFile("keyword/maximum/11.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to less than or equals to: 10.05, got: 11");
        validate(schema, instance);
    }

    @Test
    public void bad_schema_not_number() throws Exception {
        String schema = readFile("keyword/maximum/not_number.schema.json");
        String instance = readFile("keyword/maximum/11.json");

        exception.expect(BadSchemaException.class);
        exception.expectMessage("expected be numeric value, got: string");
        validate(schema, instance);
    }
}
