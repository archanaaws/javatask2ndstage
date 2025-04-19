package org.example.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ReadOutputFile {
    public static Map<String, Map<String, String>> read(String path) throws Exception {
        Map<String, Map<String, String>> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] headers = br.readLine().split(",");
            String line;
            while ((line = br.readLine()) != null) {
                String[] vals = line.split(",");
                String reg = vals[0].replaceAll("\\s", "");
                Map<String, String> detail = new HashMap<>();
                for (int i = 1; i < headers.length; i++) {
                    detail.put(headers[i].toLowerCase().trim(), vals[i].trim());
                }
                map.put(reg, detail);
            }
        }
        return map;
    }
}