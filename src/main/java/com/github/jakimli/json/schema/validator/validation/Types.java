package com.github.jakimli.json.schema.validator.validation;

import com.github.jakimli.json.schema.validator.validation.JsonType.Factory;

import static com.github.jakimli.json.schema.validator.keywords.Keyword.EXCLUSIVE_MAXIMUM;
import static com.github.jakimli.json.schema.validator.keywords.Keyword.EXCLUSIVE_MINIMUM;
import static com.github.jakimli.json.schema.validator.keywords.Keyword.MAXIMUM;
import static com.github.jakimli.json.schema.validator.keywords.Keyword.MINIMUM;
import static com.github.jakimli.json.schema.validator.keywords.Keyword.MULTIPLE_OF;
import static com.github.jakimli.json.schema.validator.keywords.Keyword.PROPERTIES;
import static com.github.jakimli.json.schema.validator.validation.Type.type;

class Types {

    static Factory arrayType() {
        return Type::type;
    }

    static Factory stringType() {
        return Type::type;
    }

    static Factory booleanType() {
        return Type::type;
    }

    static Factory integerType() {
        return schema -> type(schema)
                .keyword(MULTIPLE_OF)
                .keyword(EXCLUSIVE_MAXIMUM)
                .keyword(MAXIMUM)
                .keyword(EXCLUSIVE_MINIMUM)
                .keyword(MINIMUM)
                ;
    }

    static Factory nullType() {
        return Type::type;
    }

    static Factory numberType() {
        return schema -> type(schema)
                .keyword(MULTIPLE_OF)
                .keyword(EXCLUSIVE_MAXIMUM)
                .keyword(MAXIMUM)
                .keyword(EXCLUSIVE_MINIMUM)
                .keyword(MINIMUM)
                ;
    }

    static Factory objectType() {
        return schema -> type(schema).keyword(PROPERTIES);
    }
}
