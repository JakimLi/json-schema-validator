package com.github.jakimli.json.schema.validator.keyword.mumber;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.BadSchemaException;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class MinimumTest extends TestBase {
    @Test
    public void integer_min_but_less_than() throws Exception {
        String schema = readFile("keyword/minimum/integer.schema.json");
        String instance = readFile("keyword/minimum/9.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to greater than or equals to: 10, got: 9");
        validate(schema, instance);
    }

    @Test
    public void integer_min_equal() throws Exception {
        String schema = readFile("keyword/minimum/integer.schema.json");
        String instance = readFile("keyword/minimum/10.json");

        validate(schema, instance);
    }

    @Test
    public void integer_min_greater_than() throws Exception {
        String schema = readFile("keyword/minimum/integer.schema.json");
        String instance = readFile("keyword/minimum/11.json");

        validate(schema, instance);
    }

    @Test
    public void decimal_min_but_less_than() throws Exception {
        String schema = readFile("keyword/minimum/decimal.schema.json");
        String instance = readFile("keyword/minimum/9.05.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to greater than or equals to: 10.05, got: 9.05");
        validate(schema, instance);
    }

    @Test
    public void decimal_min_equal() throws Exception {
        String schema = readFile("keyword/minimum/decimal.schema.json");
        String instance = readFile("keyword/minimum/10.05.json");

        validate(schema, instance);
    }

    @Test
    public void decimal_min_greater_than() throws Exception {
        String schema = readFile("keyword/minimum/decimal.schema.json");
        String instance = readFile("keyword/minimum/11.05.json");

        validate(schema, instance);
    }

    @Test
    public void decimal_min_but_less_than_by_integer() throws Exception {
        String schema = readFile("keyword/minimum/decimal.schema.json");
        String instance = readFile("keyword/minimum/9.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to greater than or equals to: 10.05, got: 9");
        validate(schema, instance);
    }

    @Test
    public void bad_schema_not_number() throws Exception {
        String schema = readFile("keyword/minimum/not_number.schema.json");
        String instance = readFile("keyword/minimum/11.json");

        exception.expect(BadSchemaException.class);
        exception.expectMessage("expected be numeric value, got: string");
        validate(schema, instance);
    } 
}
