package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.validation.Validation;
import com.github.jakimli.json.schema.validator.validation.Validator;

import java.util.List;
import java.util.function.Supplier;

import static com.google.common.collect.Lists.newArrayList;

public enum Keywords {

    TYPE("type", Type::new),
    ENUM("enum", Enum::new),
    CONST("const", Const::new)
    ;

    private String word;
    private final Supplier<Keyword<Object>> supplier;

    Keywords(String word, Supplier<Keyword<Object>> supplier) {
        this.word = word;
        this.supplier = supplier;
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

    public String word() {
        return word;
    }
}
