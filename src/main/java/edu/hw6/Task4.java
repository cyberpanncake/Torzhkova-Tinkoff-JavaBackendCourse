package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    private Task4() {
    }

    public static void write(Path path, String text) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Путь не должен быть null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Текст не должен быть null");
        }
        FilePathChecker.checkPath(path, false);
        try (PrintWriter printWriter = new PrintWriter(
                 new OutputStreamWriter(
                     new BufferedOutputStream(
                         new CheckedOutputStream(
                             Files.newOutputStream(path, StandardOpenOption.CREATE),
                             new Adler32())),
                     StandardCharsets.UTF_8))) {
            printWriter.write(text);
        }
    }
}
