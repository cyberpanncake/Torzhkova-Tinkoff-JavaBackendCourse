package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task2Test {

    @ParameterizedTest
    @ValueSource(strings = {"txt", "md"})
    public void cloneFileTest(String extension) throws IOException {
        Path file = Path.of("src/main/resources/hw6/task2/Tinkoff Bank Biggest Secret.%s".formatted(extension));
        Task2.cloneFile(file);
        String copy1Name = "Tinkoff Bank Biggest Secret - копия.%s".formatted(extension);
        Path copy1Path = file.resolveSibling(copy1Name);
        Assertions.assertTrue(Files.exists(copy1Path));
        Assertions.assertArrayEquals(Files.readAllBytes(file),Files.readAllBytes(copy1Path));

        List<Path> copies = new ArrayList<>();
        for (int i = 2; i <= 10; i++) {
            Task2.cloneFile(file);
            String copyIName = "Tinkoff Bank Biggest Secret - копия (%d).%s".formatted(i, extension);
            Path copyIPath = file.resolveSibling(copyIName);
            copies.add(copyIPath);
            Assertions.assertTrue(Files.exists(copyIPath));
            Assertions.assertArrayEquals(Files.readAllBytes(file),Files.readAllBytes(copyIPath));
        }

        Files.delete(copy1Path);
        for (Path copy : copies) {
            Files.delete(copy);
        }
    }

    @Test
    public void cloneFileNotExistExceotionTest() {
        Assertions.assertThrows(NoSuchFileException.class, () -> Task2.cloneFile(Path.of("abacaba.txt")));
    }

    @Test
    public void cloneFileIsDirectoryExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.cloneFile(Path.of("src/test")));
    }
}
