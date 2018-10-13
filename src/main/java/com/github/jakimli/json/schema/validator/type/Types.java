package com.github.jakimli.json.schema.validator.type;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Types {

    public static List<Type> types() {
        return newArrayList(
                new NullType(),
                new ObjectType()
        );
    }

}
