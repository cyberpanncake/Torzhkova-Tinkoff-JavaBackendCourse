package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class DirectoryCountFilesTask extends RecursiveTask<Integer> {
    private final Path root;
    private final int countFiles;
    private final List<Path> result;

    public DirectoryCountFilesTask(Path root, int countFiles, List<Path> result) {
        this.root = root;
        this.countFiles = countFiles;
        this.result = result;
    }

    @Override
    protected Integer compute() {
        List<Path> children;
        try (Stream<Path> paths = Files.list(root)) {
            children = paths.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (children.isEmpty()) {
            return 0;
        }
        List<DirectoryCountFilesTask> childrenTasks = new LinkedList<>();
        for (Path path : children) {
            if (Files.isDirectory(path)) {
                DirectoryCountFilesTask task = new DirectoryCountFilesTask(path, countFiles, result);
                task.fork();
                childrenTasks.add(task);
            }
        }
        int count = children.size();
        for (DirectoryCountFilesTask task : childrenTasks) {
            count += task.join();
        }
        if (count > countFiles) {
            result.addFirst(root);
        }
        return count;
    }
}
