package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractPathFilter extends DirectoryStream.Filter<Path> {

    default AbstractPathFilter and(AbstractPathFilter nextFilter) {
        checkFilter(nextFilter);
        return p -> accept(p) && nextFilter.accept(p);
    }

    default AbstractPathFilter or(AbstractPathFilter nextFilter) {
        checkFilter(nextFilter);
        return p -> accept(p) || nextFilter.accept(p);
    }

    private void checkFilter(AbstractPathFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("Фильтр не должен быть null");
        }
    }
}
