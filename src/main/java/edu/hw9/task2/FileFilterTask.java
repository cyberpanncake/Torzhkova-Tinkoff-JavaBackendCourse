package edu.hw9.task2;

import edu.hw6.task3.AbstractPathFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileFilterTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final AbstractPathFilter filter;

    public FileFilterTask(Path root, AbstractPathFilter filter) {
        this.root = root;
        this.filter = filter.or(Files::isDirectory);
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new LinkedList<>();
        List<Path> children = new LinkedList<>();
        try (DirectoryStream<Path> dirs = Files.newDirectoryStream(root, filter)) {
            dirs.forEach(path -> {
                if (Files.isDirectory(path)) {
                    children.add(path);
                } else {
                    result.add(path);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<FileFilterTask> nextTasks = new LinkedList<>();
        for (Path path : children) {
            FileFilterTask task = new FileFilterTask(path, filter);
            task.fork();
            nextTasks.add(task);
        }
        for (FileFilterTask task : nextTasks) {
            result.addAll(task.join());
        }
        return result;
    }
}
