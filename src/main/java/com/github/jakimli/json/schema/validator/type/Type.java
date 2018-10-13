package com.github.jakimli.json.schema.validator.type;

import com.alibaba.fastjson.JSONObject;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.List;

import static com.github.jakimli.json.schema.validator.exception.InvalidKeywordException.invalidKeywordException;
import static java.util.Arrays.stream;

public enum Type {

    OBJECT("object", ObjectType::new);

    private String keyword;
    private Factory factory;

    Type(String keyword, Factory factory) {
        this.keyword = keyword;
        this.factory = factory;
    }

    public static Type typeOf(String keyword) {
        return stream(Type.values())
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
