package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task4Test {

    @Test
    public void write() throws IOException {
        Path path = Path.of("src/main/resources/hw6/task4").resolve("file.txt");
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        Task4.write(path, text);
        Assertions.assertEquals(text, Files.readString(path));
    }
}
