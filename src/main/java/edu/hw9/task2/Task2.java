package edu.hw9.task2;

import edu.hw6.task3.AbstractPathFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Task2 {
    private Task2() {}

    public static List<Path> getDirectoriesWithCountFilesBiggerThan(Path root, int countFiles) {
        checkDirectory(root);
        List<Path> result = Collections.synchronizedList(new LinkedList<>());
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DirectoryCountFilesTask task = new DirectoryCountFilesTask(root, countFiles, result);
            forkJoinPool.invoke(task);
            return result;
        }
    }

    public static List<Path> getFilesMatchFilter(Path root, AbstractPathFilter filter) {
        checkDirectory(root);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FileFilterTask task = new FileFilterTask(root, filter);
            return forkJoinPool.invoke(task);
        }
    }

    private static void checkDirectory(Path path) {
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Путь должен быть папкой");
        }
    }
}
