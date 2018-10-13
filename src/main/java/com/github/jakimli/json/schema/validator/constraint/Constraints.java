package com.github.jakimli.json.schema.validator.constraint;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.jakimli.json.schema.validator.type.Types.types;
import static com.github.jakimli.json.schema.validator.util.JsonUtil.toMap;

public class Constraints {


    public static List<Constraint> constraints(String jsonSchema) {
        Map<String, Object> schema = toMap(jsonSchema);
        return types().stream()
                .filter(type -> type.isType(schema))
                .map(type -> type.constraints(schema))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
