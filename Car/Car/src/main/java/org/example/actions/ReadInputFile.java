package org.example.actions;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadInputFile {
    public static List<String> read(String inputFilePath) throws Exception {
        List<String> regNumbers = new ArrayList<>();
        Pattern p = Pattern.compile("[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}");
        List<String> lines = Files.readAllLines(new File(inputFilePath).toPath());
        for (String line : lines) {
            Matcher m = p.matcher(line);
            while (m.find()) regNumbers.add(m.group().replaceAll("\\s", ""));
        }
        return regNumbers;
    }
}