package com.github.jakimli.json.schema.validator;

import com.github.jakimli.json.schema.validator.exception.SchemaViolatedException;
import com.github.jakimli.json.schema.validator.validation.Schema;

import static com.alibaba.fastjson.JSON.parse;
import static com.alibaba.fastjson.JSON.parseObject;

public class SchemaValidator {
    public static void validate(String jsonSchema, String jsonInstance) {
        if (alwaysTrue(jsonSchema)) {
            return;
        }

        if (alwaysFalse(jsonSchema)) {
            throw new SchemaViolatedException("always fail");
        }

        Schema schema = new Schema("$", parseObject(jsonSchema));
        schema.validate().forEach(validation -> validation.validate(parse(jsonInstance)));
    }

    private static boolean alwaysTrue(String schema) {
        Object actual = parse(schema);
        return (parse("{}").equals(actual) || parse("true").equals(actual));
    }

    private static boolean alwaysFalse(String schema) {
        return parse("false").equals(parse(schema));
    }
}
