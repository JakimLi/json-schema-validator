package com.github.jakimli.json.schema.validator.validation;

import com.alibaba.fastjson.JSONObject;

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

    public Validator validator(String location, JSONObject schema) {
        return this.factory.create(location, schema);
    }

    interface Factory {
        Validator create(String location, JSONObject schema);
    }

    public interface Validator {
        List<Validation> validate();
    }
}
