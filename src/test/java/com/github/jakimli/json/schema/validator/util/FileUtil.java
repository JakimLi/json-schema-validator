package com.github.jakimli.json.schema.validator.util;

import com.google.common.io.Resources;

import java.io.IOException;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.Resources.getResource;

public class FileUtil {
    public static String readFile(String name) throws IOException {
        return Resources.toString(getResource(name), UTF_8);
    }
}
