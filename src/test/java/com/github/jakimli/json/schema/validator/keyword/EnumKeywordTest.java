package com.github.jakimli.json.schema.validator.keyword;

import com.github.jakimli.json.schema.validator.TestBase;
import com.github.jakimli.json.schema.validator.exception.BadSchemaException;
import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import org.junit.Test;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class EnumKeywordTest extends TestBase {

    @Test
    public void should_validate_enum_with_error() throws Exception {
        String schema = readFile("keyword/enum/string.schema.json");
        String instance = readFile("keyword/enum/string.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be one of values in enum: [monica, chandler], got: joey");
        validate(schema, instance);
    }

    @Test
    public void enum_not_an_array() throws Exception {
        String schema = readFile("keyword/enum/not.array.schema.json");
        String instance = readFile("keyword/enum/valid.json");

        exception.expect(BadSchemaException.class);
        exception.expectMessage("expected type array, got: not array");
        validate(schema, instance);
    }

    @Test
    public void should_validate_enum_without_error() throws Exception {
        String schema = readFile("keyword/enum/string.schema.json");
        String instance = readFile("keyword/enum/valid.json");

        validate(schema, instance);
    }

    @Test
    public void should_validate_when_enum_is_empty_array() throws Exception {
        String schema = readFile("keyword/enum/empty.array.schema.json");
        String instance = readFile("keyword/enum/valid.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be one of values in enum: [], got: monica");
        validate(schema, instance);
    }

    @Test
    public void should_validate_when_enum_has_duplicates() throws Exception {
        String schema = readFile("keyword/enum/duplicate.schema.json");
        String instance = readFile("keyword/enum/valid.json");

        validate(schema, instance);
    }

    @Test
    public void should_validate_object_in_enum() throws Exception {
        String schema = readFile("keyword/enum/object.schema.json");
        String instance = readFile("keyword/enum/object.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be one of values in enum: [{\"name\":\"monica\"}, {\"name\":\"chandler\"}]");
        validate(schema, instance);
    }

    @Test
    public void should_validate_string_fail_with_mix_type_in_enum() throws Exception {
        String schema = readFile("keyword/enum/mix.schema.json");
        String instance = readFile("keyword/enum/string.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be one of values in enum: [{\"name\":\"monica\"}, chandler], got: joey");
        validate(schema, instance);
    }

    @Test
    public void should_validate_object_fail_with_mix_type_in_enum() throws Exception {
        String schema = readFile("keyword/enum/mix.schema.json");
        String instance = readFile("keyword/enum/object.json");

        exception.expect(SchemaViolatedException.class);
        exception.expectMessage("must be one of values in enum: [{\"name\":\"monica\"}, chandler], got: {\"name\":\"joey\"}");
        validate(schema, instance);
    }

    @Test
    public void should_validate_success_with_mix_type_in_enum() throws Exception {
        String schema = readFile("keyword/enum/mix.schema.json");
        String instance = readFile("keyword/enum/mix.object.json");

        validate(schema, instance);
    }
}
