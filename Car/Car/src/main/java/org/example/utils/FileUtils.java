package org.example.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String INPUT_DIR = "input";

    public static List<File> getInputFiles() {
        File dir = new File(INPUT_DIR);
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().startsWith("car_input") && name.endsWith(".txt"));
        List<File> list = new ArrayList<>();
        if (files != null) for (File f : files) list.add(f);
        return list;
    }
    public static File getMatchingOutputFile(File inputFile) {
        String inputName = inputFile.getName().toLowerCase().replace("input", "output");
        File outputFile = new File("input" + File.separator + inputName);
        return outputFile.exists() ? outputFile : null;
    }


    public static String getAbsolutePath(File file) {
        return file.getAbsolutePath();
    }
}