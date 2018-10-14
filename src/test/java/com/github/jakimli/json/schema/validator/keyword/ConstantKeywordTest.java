package com.github.jakimli.json.schema.validator.keyword;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class ConstantKeywordTest extends TestBase {

    @Test
    public void constant_string_invalid() throws Exception {
        String schema = readFile("keyword/const/const.string.schema.json");
        String instance = readFile("keyword/const/string.invalid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be: monica, got: chandler");
        validate(schema, instance);
    }

    @Test
    public void constant_object_invalid() throws Exception {
        String schema = readFile("keyword/const/const.object.schema.json");
        String instance = readFile("keyword/const/string.invalid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be: {\"name\":\"monica\"}, got: chandler");
        validate(schema, instance);
    }

    @Test
    public void constant_object_valid() throws Exception {
        String schema = readFile("keyword/const/const.object.schema.json");
        String instance = readFile("keyword/const/object.valid.json");

        validate(schema, instance);
    }
}
