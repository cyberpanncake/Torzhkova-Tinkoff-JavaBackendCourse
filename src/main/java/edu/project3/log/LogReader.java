package edu.project3.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogReader {
    private LogReader() {
    }

    public static List<Log> read(String url) throws IOException, IllegalLogException {
        List<Log> logs = new ArrayList<>();
        List<File> files = List.of(new File(url));
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                logs.addAll(reader.lines()
                    .map(LogRowMapper::map)
                    .toList());
            } catch (IOException e) {
                throw new IOException("Не удалось прочитать логи из файла(-ов)");
            }
        }
        return logs;
    }
}
