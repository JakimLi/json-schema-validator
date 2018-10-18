package com.github.jakimli.json.schema.validator.keywords;

import com.github.jakimli.json.schema.validator.keywords.number.ExclusiveMaximum;
import com.github.jakimli.json.schema.validator.keywords.number.ExclusiveMinimum;
import com.github.jakimli.json.schema.validator.keywords.number.Maximum;
import com.github.jakimli.json.schema.validator.keywords.number.Minimum;
import com.github.jakimli.json.schema.validator.keywords.number.MultipleOf;
import com.github.jakimli.json.schema.validator.validation.Validation;
import com.github.jakimli.json.schema.validator.validation.Type;

import java.util.List;
import java.util.function.Supplier;

import static com.google.common.collect.Lists.newArrayList;

public enum Keyword {

    TYPE("type", com.github.jakimli.json.schema.validator.keywords.Type::new),
    ENUM("enum", Enum::new),
    CONST("const", Const::new),

    MULTIPLE_OF("multipleOf", MultipleOf::new),
    MAXIMUM("maximum", Maximum::new),
    EXCLUSIVE_MAXIMUM("exclusiveMaximum", ExclusiveMaximum::new),
    MINIMUM("minimum", Minimum::new),
    EXCLUSIVE_MINIMUM("exclusiveMinimum", ExclusiveMinimum::new),

    PROPERTIES("properties", Properties::new);

    private String word;
    private final Supplier<Validator> supplier;

    Keyword(String word, Supplier<Validator> supplier) {
        this.word = word;
        this.supplier = supplier;
    }

    public List<Validation> validate(Type schema) {
        Object element = schema.sub(word);

        if (element == null) {
            return newArrayList();
        }

        return supplier.get().validate(schema.location(), element);
    }

    public interface Validator {
        List<Validation> validate(String location, Object value);
    }
}
