package com.github.jakimli.json.schema.validator.validation;

import com.github.jakimli.json.schema.validator.keywords.Keyword;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.keywords.Keyword.CONST;
import static com.github.jakimli.json.schema.validator.keywords.Keyword.ENUM;
import static com.github.jakimli.json.schema.validator.keywords.Keyword.TYPE;
import static com.google.common.collect.Lists.newArrayList;

public class Type implements JsonType.Validator {
    private final Schema schema;

    private List<Keyword> keywords = newArrayList(ENUM, CONST, TYPE);

    private Type(Schema schema) {
        this.schema = schema;
    }

    public static Type type(Schema schema) {
        return new Type(schema);
    }

    @Override
    public List<Validation> validate() {
        return validateByKeywords();
    }

    private List<Validation> validateByKeywords() {
        return this.keywords.stream()
                .map(k -> k.validate(this))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public String location() {
        return schema.location();
    }

    public Object sub(String word) {
        return schema.get(word);
    }

    Type keyword(Keyword keyword) {
        this.keywords.add(keyword);
        return this;
    }
}
