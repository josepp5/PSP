package com.example.linktrackerfx.utils;

import com.example.linktrackerfx.model.WebPage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    public static List<WebPage> loadPages(Path file) {
        try {
            return Files.lines(Paths.get(file.toUri()))
                    .map(line -> new WebPage(line.split(";")[0],
                            line.split(";")[1]))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
