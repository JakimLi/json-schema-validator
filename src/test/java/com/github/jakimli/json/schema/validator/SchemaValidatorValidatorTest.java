package com.github.jakimli.json.schema.validator;

import com.github.jakimli.json.schema.validator.exception.InvalidException;
import org.junit.Test;

import java.io.IOException;

import static com.github.jakimli.json.schema.validator.SchemaValidator.validate;
import static com.github.jakimli.json.schema.validator.util.FileUtil.readFile;

public class SchemaValidatorValidatorTest {

    @Test
    public void no_constraints() {
        validate("{}", "{\"any\": \"json\"}");
    }

    @Test
    public void validate_null_type() throws Exception {
        validate(schema("null_type"), validInstance("null_type"));
    }

    @Test(expected = InvalidException.class)
    public void validate_null_type_invalid() throws Exception {
        validate(schema("null_type"), invalidInstance("null_type"));
    }

    private String invalidInstance(String type) throws IOException {
        return readFile("type_specific/" + type + "/invalid_instance.json");
    }

    private String schema(String type) throws IOException {
        return readFile("type_specific/" + type + "/schema.json");
    }

    private String validInstance(String type) throws IOException {
        return readFile("type_specific/" + type + "/valid_instance.json");
    }
}
