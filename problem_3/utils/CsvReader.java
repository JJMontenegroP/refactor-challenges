package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<String[]> readCsv(String filePath, String delimiter) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Leer cabecera
            while ((line = br.readLine()) != null) {
                data.add(line.split(delimiter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
