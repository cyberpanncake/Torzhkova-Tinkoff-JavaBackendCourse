package edu.hw6;

import edu.hw6.task1.DiskMap;
import edu.hw6.task1.DiskMapException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Task1Test {
    private static final Path MAP_PATH = Path.of("src/main/resources/hw6/task1/map.txt");
    private static final Map<String,String> map = Map.of(
        "1", "task1",
        "2", "task2",
        "3", "task1");
    private static final String TEXT = """
        1:task1
        2:task2
        3:task1
        """;

    @Test
    public void readTest() throws IOException {
        DiskMap diskMap = DiskMap.read(MAP_PATH);
        Assertions.assertEquals(map, diskMap);
    }

    @Test
    public void readBadMapExceptionTest() {
        Assertions.assertThrows(DiskMapException.class,
            () -> DiskMap.read(MAP_PATH.resolveSibling("bad_map.txt")));
    }

    @Test
    public void readNotExistFileMapExceptionTest() {
        Assertions.assertThrows(NoSuchFileException.class,
            () -> DiskMap.read(MAP_PATH.resolveSibling("not_exist.txt")));
    }


    @Test
    public void readFolderMapExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> DiskMap.read(Path.of("src")));
    }

    @Test
    public void writeTest() throws IOException {
        DiskMap diskMap = DiskMap.read(MAP_PATH);
        Path path = MAP_PATH.resolveSibling("map_copy.txt");
        diskMap.write(path);
        diskMap = DiskMap.read(path);
        Assertions.assertEquals(map, diskMap);
        Files.deleteIfExists(path);
    }

    @Test
    public void putTest() throws IOException {
        DiskMap diskMap = DiskMap.read(MAP_PATH);
        String nullResult = diskMap.put("4", "task3");
        Assertions.assertNull(nullResult);
        setFileDataToInit();
    }

    @Test
    public void getTest() throws IOException {
        DiskMap diskMap = DiskMap.read(MAP_PATH);
        Assertions.assertEquals("task1", diskMap.get("1"));
    }

    private void setFileDataToInit() throws IOException {
        Files.writeString(MAP_PATH, TEXT);
    }
}
