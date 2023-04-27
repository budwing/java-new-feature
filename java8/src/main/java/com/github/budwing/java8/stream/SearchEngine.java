package com.github.budwing.java8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SearchEngine {
    public static List<String> searchFilesContains(String directory, String keyword) throws IOException {
        return Files.walk(Paths.get(directory))
                .map(path -> {
                    try {
                        return new Object[]{path, Files.readAllLines(path, Charset.defaultCharset())};
                    } catch (IOException e) {
                        return new Object[] {path, ""};
                    }
                }).filter(objects -> objects[1].toString().contains(keyword))
                .map(objects -> objects[0].toString())
                .collect(Collectors.toList());
    }
}
