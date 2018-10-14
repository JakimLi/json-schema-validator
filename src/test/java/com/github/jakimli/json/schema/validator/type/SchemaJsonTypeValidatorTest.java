package com.github.jakimli.json.schema.validator.type;

import com.github.jakimli.json.schema.validator.exception.InvalidSchemaException;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.alibaba.fastjson.JSON.parse;
import static com.github.jakimli.json.schema.validator.Validator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class SchemaJsonTypeValidatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_not_null_type() throws Exception {

        String schema = readFile("type/null/schema.json");
        String instance = readFile("type/null/invalid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [NULL], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_null_type() throws Exception {
        String schema = readFile("type/null/schema.json");
        String instance = readFile("type/null/valid.json");

        validate(schema, instance);
    }

    @Test
    public void validate_not_boolean_type() throws Exception {
        String schema = readFile("type/boolean/schema.json");
        String instance = readFile("type/boolean/invalid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [BOOLEAN], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_boolean_type() throws Exception {
        String schema = readFile("type/boolean/schema.json");
        String instance = readFile("type/boolean/valid.json");

        validate(schema, instance);
    }

    @Test
    public void validate_object_type_but_boolean() throws Exception {
        String schema = readFile("type/object/schema.json");
        String instance = readFile("type/object/boolean.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [OBJECT], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_object_type_property_null_type_but_string() throws Exception {
        String schema = readFile("type/object/schema.json");
        String instance = readFile("type/object/invalid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [NULL], got: " + "wrong");
        validate(schema, instance);
    }

    @Test
    public void validate_valid_object() throws Exception {
        String schema = readFile("type/object/schema.json");
        String instance = readFile("type/object/valid.json");

        validate(schema, instance);
    }

    @Test
    public void validate_integer_type_but_array() throws Exception {
        String schema = readFile("type/integer/schema.json");
        String instance = readFile("type/integer/array.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [INTEGER], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_integer() throws Exception {
        String schema = readFile("type/integer/schema.json");
        String instance = readFile("type/integer/integer.json");

        validate(schema, instance);
    }

    @Test
    public void validate_array_but_object() throws Exception {
        String schema = readFile("type/array/schema.json");
        String instance = readFile("type/array/object.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [ARRAY], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_array() throws Exception {
        String schema = readFile("type/array/schema.json");
        String instance = readFile("type/array/array.json");

        validate(schema, instance);
    }

    @Test
    public void validate_array_in_object_but_got_object() throws Exception {
        String schema = readFile("type/array/nested/schema.json");
        String instance = readFile("type/array/nested/object.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [ARRAY], got: {\"not\":\"array\"}");
        validate(schema, instance);
    }
    
    @Test
    public void validate_array_in_object() throws Exception {
        String schema = readFile("type/array/nested/schema.json");
        String instance = readFile("type/array/nested/array.json");

        validate(schema, instance);
    }

    @Test
    public void validate_number_but_integer() throws Exception {
        String schema = readFile("type/number/schema.json");
        String instance = readFile("type/number/integer.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [NUMBER], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_number() throws Exception {
        String schema = readFile("type/number/schema.json");
        String instance = readFile("type/number/number.json");

        validate(schema, instance);
    }

    @Test
    public void validate_string_but_integer() throws Exception {
        String schema = readFile("type/string/schema.json");
        String instance = readFile("type/string/integer.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [STRING], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_string() throws Exception {
        String schema = readFile("type/string/schema.json");
        String instance = readFile("type/string/string.json");

        validate(schema, instance);
    }

    @Test
    public void validate_multiple_type() throws Exception {
        String schema = readFile("type/multiple/schema.json");

        String instance = readFile("type/multiple/string.json");
        validate(schema, instance);

        instance = readFile("type/multiple/integer.json");
        validate(schema, instance);

        instance = readFile("type/multiple/number.json");
        validate(schema, instance);

        instance = readFile("type/multiple/object.json");
        validate(schema, instance);

        instance = readFile("type/multiple/null.json");
        validate(schema, instance);

        instance = readFile("type/multiple/boolean.json");
        validate(schema, instance);
    }

    @Test
    public void validate_multiple_type_but_not_included() throws Exception {
        String schema = readFile("type/multiple/schema.json");
        String instance = readFile("type/multiple/array.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [STRING, INTEGER, NULL, BOOLEAN, NUMBER, OBJECT], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_duplicate_type_in_array() throws Exception {
        String schema = readFile("type/multiple/duplicate.type.schema.json");
        String instance = readFile("type/multiple/array.json");

        exception.expect(InvalidSchemaException.class);
        exception.expectMessage("types must be unique");
        validate(schema, instance);
    }
}