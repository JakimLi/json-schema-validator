package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validator;
import com.github.jakimli.json.schema.validator.validation.Validation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.github.jakimli.json.schema.validator.exception.InvalidKeywordException.invalidKeywordException;
import static com.google.common.collect.Lists.newArrayList;

public enum Keywords {

    TYPE("type", Type::new),
    ENUM("enum", Enum::new),
    ;

    private String word;
    private final Supplier<Keyword<Object>> supplier;

    Keywords(String word, Supplier<Keyword<Object>> supplier) {
        this.word = word;
        this.supplier = supplier;
    }

    public static Keywords byKeyword(String keyword) {
        return Arrays.stream(Keywords.values())
                .filter(key -> key.word.equals(keyword))
                .findFirst()
                .orElseThrow(() -> invalidKeywordException(keyword));
    }

    public Keyword<Object> get() {
        return supplier.get();
    }

    public List<Validation> validate(Validator schema) {
        Object element = schema.sub(word);

        if (element == null) {
            return newArrayList();
        }

        return this.get().validations(schema.location(), element);
    }
}
