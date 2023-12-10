package edu.hw6;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class FilePathChecker {

    private FilePathChecker() {
    }

    public static void checkPath(Path path, boolean checkExist) throws NoSuchFileException {
        if (checkExist && !Files.exists(path)) {
            throw new NoSuchFileException("Файл не существует");
        }
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("Файл не должен быть папкой");
        }
    }

    public static void checkPath(Path path) throws NoSuchFileException {
        checkPath(path, true);
    }
}
