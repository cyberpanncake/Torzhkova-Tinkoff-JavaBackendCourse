package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Task2 {
    public static final String SUFFIX = " - копия";

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Путь не должен быть null");
        }
        FilePathChecker.checkPath(path, true);
        Path coptPath = getCopyPath(path);
        Files.copy(path, coptPath, StandardCopyOption.COPY_ATTRIBUTES, LinkOption.NOFOLLOW_LINKS);
    }

    private static Path getCopyPath(Path source) {
        String filenameWithExtension = source.getFileName().toString();
        String[] splitName = filenameWithExtension.split("\\.(?=[^.]*$)");
        String filename = splitName[0];
        String extension = "";
        if (splitName.length > 1) {
            extension = splitName[1];
        }
        String copy1Name = "%s%s.%s".formatted(filename, SUFFIX, extension);
        Path copy1Path = source.resolveSibling(copy1Name);
        if (!Files.exists(copy1Path)) {
            return copy1Path;
        }
        int i = 1;
        Path currentClonePath;
        do {
            String currentCloneName = "%s%s (%d).%s".formatted(filename, SUFFIX, ++i, extension);
            currentClonePath = source.resolveSibling(currentCloneName);
        } while (Files.exists(currentClonePath));
        return currentClonePath;
    }
}
