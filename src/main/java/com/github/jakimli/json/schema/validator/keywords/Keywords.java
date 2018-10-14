package com.github.jakimli.json.schema.validator.keywords;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.github.jakimli.json.schema.validator.exception.InvalidKeywordException.invalidKeywordException;

public enum Keywords {

    TYPE("type", Type::new);

    private String word;
    private final Supplier<Keyword> supplier;

    Keywords(String word, Supplier<Keyword> supplier) {
        this.word = word;
        this.supplier = supplier;
    }

    public static Keywords byKeyword(String keyword) {
        return Arrays.stream(Keywords.values())
                .filter(key -> key.word.equals(keyword))
                .findFirst()
                .orElseThrow(() -> invalidKeywordException(keyword));
    }

    public Keyword get() {
        return supplier.get();
    }
}
