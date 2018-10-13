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
    public void validate_not_null_type() throws Exception {

        String schema = readFile("type_specific/null/schema.json");
        String instance = readFile("type_specific/null/invalid.json");

        exception.expect(ViolateJsonSchemaException.class);
        exception.expectMessage("expected null, got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_null_type() throws Exception {
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

    @Test
    public void validate_boolean_type() throws Exception {
        String schema = readFile("type_specific/boolean/schema.json");
        String instance = readFile("type_specific/boolean/valid.json");

        validate(schema, instance);
    }

    @Test
    public void validate_object_type_but_boolean() throws Exception {
        String schema = readFile("type_specific/object/schema.json");
        String instance = readFile("type_specific/object/boolean.json");

        exception.expect(ViolateJsonSchemaException.class);
        exception.expectMessage("expected type object, got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_object_type_property_null_type_but_string() throws Exception {
        String schema = readFile("type_specific/object/schema.json");
        String instance = readFile("type_specific/object/invalid.json");

        exception.expect(ViolateJsonSchemaException.class);
        exception.expectMessage("expected null, got: " + "wrong");
        validate(schema, instance);
    }

    @Test
    public void validate_valid_object() throws Exception {
        String schema = readFile("type_specific/object/schema.json");
        String instance = readFile("type_specific/object/valid.json");

        validate(schema, instance);
    }

    @Test
    public void validate_integer_type_but_array() throws Exception {
        String schema = readFile("type_specific/integer/schema.json");
        String instance = readFile("type_specific/integer/array.json");

        exception.expect(ViolateJsonSchemaException.class);
        exception.expectMessage("expected type integer, got: " + parse(instance));
        validate(schema, instance);
    }
}
