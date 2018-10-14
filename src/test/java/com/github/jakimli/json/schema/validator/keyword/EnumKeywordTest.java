package com.github.jakimli.json.schema.validator.keyword;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class EnumKeywordTest extends TestBase {

    @Test
    public void should_validate_enum_with_error() throws Exception {
        String schema = readFile("keyword/enum/schema.json");
        String instance = readFile("keyword/enum/invalid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be one of values in enum: [monica, chandler], got: joey");
        validate(schema, instance);
    }

    @Test
    public void should_validate_enum_without_error() throws Exception {
        String schema = readFile("keyword/enum/schema.json");
        String instance = readFile("keyword/enum/valid.json");

        validate(schema, instance);
    }
}
