package com.github.jakimli.json.schema.validator.keyword.mumber;

import com.github.jakimli.json.schema.validator.TestBase;
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
}
