package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.exception.InvalidKeywordException.invalidKeywordException;
import static java.util.Arrays.stream;

public enum JsonType {

    OBJECT("object", ObjectType::new),
    NULL("null", NullType::new),
    BOOLEAN("boolean", BooleanType::new),
    INTEGER("integer", IntegerType::new),
    NUMBER("number", NumberType::new),
    ARRAY("array", ArrayType::new),
    STRING("string", StringType::new);

    private String keyword;
    private Factory factory;

    JsonType(String keyword, Factory factory) {
        this.keyword = keyword;
        this.factory = factory;
    }

    public static JsonType typeOf(String keyword) {
        return stream(JsonType.values())
                .filter(type -> type.keyword.equals(keyword))
                .findFirst()
                .orElseThrow(() -> invalidKeywordException(keyword));
    }

    public JsonSchema schema(String location, JSONObject schema) {
        return this.factory.create(location, schema);
    }

    interface Factory {
        JsonSchema create(String location, JSONObject schema);
    }

    public interface JsonSchema {
        List<Validation> validations();
    }
}
