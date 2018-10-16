package com.github.jakimli.json.schema.validator.validation;

import com.github.jakimli.json.schema.validator.validation.JsonType.Factory;

import static com.github.jakimli.json.schema.validator.keywords.Keywords.PROPERTIES;
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
        return Type::type;
    }

    static Factory nullType() {
        return Type::type;
    }

    static Factory numberType() {
        return Type::type;
    }

    static Factory objectType() {
        return schema -> type(schema).keyword(PROPERTIES);
    }
}
