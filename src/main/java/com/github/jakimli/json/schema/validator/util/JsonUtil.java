package com.github.jakimli.json.schema.validator.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

public class JsonUtil {

    public static Map<String, Object> toMap(String json) {
        return JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
        });
    }
}
