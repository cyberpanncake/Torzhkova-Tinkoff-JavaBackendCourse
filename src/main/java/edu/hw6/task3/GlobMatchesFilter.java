package edu.hw6.task3;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class GlobMatchesFilter implements AbstractPathFilter {
    private final PathMatcher matcher;

    public GlobMatchesFilter(String glob) {
        if (glob == null) {
            throw new IllegalArgumentException("Шаблон не должен быть null");
        }
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
    }

    @Override
    public boolean accept(Path path) {
        return matcher.matches(path.getFileName());
    }
}
