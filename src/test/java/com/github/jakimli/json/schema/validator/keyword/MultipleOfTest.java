package com.github.jakimli.json.schema.validator.keyword;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class MultipleOfTest extends TestBase {


    @Test
    public void validate_multiple_of_fail_integer() throws Exception {
        String schema = readFile("keyword/multipleOf/schema.json");
        String instance = readFile("keyword/multipleOf/invalid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected to be multiple of: 10, got: 11");
        validate(schema, instance);
    }

    @Test
    public void validate_multiple_of_sucess_integer() throws Exception {
        String schema = readFile("keyword/multipleOf/schema.json");
        String instance = readFile("keyword/multipleOf/20.json");

        validate(schema, instance);
    }
}
