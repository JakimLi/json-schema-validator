package com.github.jakimli.json.schema.validator.keyword;

import com.github.jakimli.json.schema.validator.exception.InvalidKeywordException;
import com.github.jakimli.json.schema.validator.exception.InvalidSchemaException;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.alibaba.fastjson.JSON.parse;
import static com.github.jakimli.json.schema.validator.Validator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class KeywordTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_multiple_type() throws Exception {
        String schema = readFile("keyword/type/schema.json");

        String instance = readFile("keyword/type/string.json");
        validate(schema, instance);

        instance = readFile("keyword/type/integer.json");
        validate(schema, instance);

        instance = readFile("keyword/type/number.json");
        validate(schema, instance);

        instance = readFile("keyword/type/object.json");
        validate(schema, instance);

        instance = readFile("keyword/type/null.json");
        validate(schema, instance);

        instance = readFile("keyword/type/boolean.json");
        validate(schema, instance);
    }

    @Test
    public void validate_multiple_type_but_not_included() throws Exception {
        String schema = readFile("keyword/type/schema.json");
        String instance = readFile("keyword/type/array.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("expected one of type: [STRING, INTEGER, NULL, BOOLEAN, NUMBER, OBJECT], got: " + parse(instance));
        validate(schema, instance);
    }

    @Test
    public void validate_duplicate_type_in_array() throws Exception {
        String schema = readFile("keyword/type/duplicate.type.schema.json");
        String instance = readFile("keyword/type/string.json");

        exception.expect(InvalidSchemaException.class);
        exception.expectMessage("types must be unique");
        validate(schema, instance);
    }

    @Test
    public void validate_empty_type_array() throws Exception {
        String schema = readFile("keyword/type/empty.type.schema.json");
        String instance = readFile("keyword/type/string.json");

        exception.expect(InvalidSchemaException.class);
        exception.expectMessage("type must be a string or a non-empty array");
        validate(schema, instance);
    }

    @Test
    public void validate_type_has_non_string() throws Exception {
        String schema = readFile("keyword/type/nonstring.type.schema.json");
        String instance = readFile("keyword/type/string.json");

        exception.expect(InvalidKeywordException.class);
        exception.expectMessage("Invalid keyword: 1");
        validate(schema, instance);
    }
}
