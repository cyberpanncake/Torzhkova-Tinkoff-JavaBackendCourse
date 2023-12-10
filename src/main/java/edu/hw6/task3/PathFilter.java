package edu.hw6.task3;

import java.nio.file.Files;

public final class PathFilter {
    public static final AbstractPathFilter WRITABLE = Files::isWritable;
    public static final AbstractPathFilter READABLE = Files::isReadable;

    private PathFilter() {
    }

    public static AbstractPathFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    public static AbstractPathFilter lessThan(long size) {
        return path -> Files.size(path) < size;
    }

    public static AbstractPathFilter matches(String regex) {
        if (regex == null) {
            throw new IllegalArgumentException("Регулярное выражение не должно быть null");
        }
        return path -> path.getFileName().toString().matches(regex);
    }
}
