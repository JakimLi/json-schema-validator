package com.github.jakimli.json.schema.validator.type;

import com.github.jakimli.json.schema.validator.exception.ViolateJsonSchemaException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.alibaba.fastjson.JSON.parse;
import static com.github.jakimli.json.schema.validator.Validator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class SchemaTypeValidatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_not_null() throws Exception {

        String schema = readFile("type_specific/null/schema.json");
        String instance = readFile("type_specific/null/invalid.json");

        exception.expect(ViolateJsonSchemaException.class);
        exception.expectMessage("expected null, got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_null() throws Exception {
        String schema = readFile("type_specific/null/schema.json");
        String instance = readFile("type_specific/null/valid.json");

        validate(schema, instance);
    }

    @Test
    public void validate_not_boolean_type() throws Exception {
        String schema = readFile("type_specific/boolean/schema.json");
        String instance = readFile("type_specific/boolean/invalid.json");

        exception.expect(ViolateJsonSchemaException.class);
        exception.expectMessage("expected type boolean, got: " + parse(instance));
        validate(schema, instance);
    }
}
