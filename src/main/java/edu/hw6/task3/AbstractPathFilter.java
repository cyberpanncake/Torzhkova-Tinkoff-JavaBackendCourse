package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractPathFilter extends DirectoryStream.Filter<Path> {

    default AbstractPathFilter and(AbstractPathFilter nextFilter) {
        if (nextFilter == null) {
            throw new IllegalArgumentException("Фильтр не должен быть null");
        }
        return p -> accept(p) && nextFilter.accept(p);
    }
}
