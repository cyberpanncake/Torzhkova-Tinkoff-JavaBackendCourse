package edu.hw9;

import edu.hw6.task3.AbstractPathFilter;
import edu.hw6.task3.GlobMatchesFilter;
import edu.hw6.task3.PathFilter;
import edu.hw9.task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Task2Test {
    @TempDir
    Path root;

    @BeforeEach
    void createFileTree() throws IOException {
        Path d1 = Files.createDirectory(root.resolve("d1"));
        Path d11 = Files.createDirectory(d1.resolve("d11"));
        Path d2 = Files.createDirectory(root.resolve("d2"));
        Path d21 = Files.createDirectory(d2.resolve("d21"));

        Path f1 = Files.createFile(root.resolve("f1.txt"));
        Files.write(f1, new byte[23]);
        Path f11 = Files.createFile(d1.resolve("f11.png"));
        Files.write(f11, new byte[10]);
        Path f111 = Files.createFile(d11.resolve("f111.txt"));
        Files.write(f111, new byte[3]);
        Path f2 = Files.createFile(d2.resolve("f2.png"));
        Files.write(f2, new byte[7]);
        Path f21 = Files.createFile(d21.resolve("f21.bmp"));
        Files.write(f21, new byte[17]);
    }

    @Test
    void getDirectoriesWithCountFilesBiggerThanTest() {
        List<Path> expected = List.of(
            root,
            root.resolve("d1"),
            root.resolve("d2"));
        List<Path> actual = Task2.getDirectoriesWithCountFilesBiggerThan(root, 2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getFilesMatchFilterTest() {
        List<Path> expected = List.of(
            root.resolve("f1.txt"),
            root.resolve("d1").resolve("f11.png"),
            root.resolve("d2").resolve("f2.png"));
        AbstractPathFilter filter = PathFilter.largerThan(10)
            .and(new GlobMatchesFilter("*.txt"))
            .or(new GlobMatchesFilter("*.png"));
        List<Path> actual = Task2.getFilesMatchFilter(root, filter);
        Assertions.assertEquals(expected, actual);
    }
}
