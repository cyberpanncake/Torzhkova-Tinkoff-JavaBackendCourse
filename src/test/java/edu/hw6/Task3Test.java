package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import edu.hw6.task3.GlobMatchesFilter;
import edu.hw6.task3.MagicNumberFilter;
import edu.hw6.task3.PathFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task3Test {
    private static final Path DIRECTORY = Path.of("src/main/resources/hw6/task3");

    @Test
    public void largerThanFilterTest() throws IOException {
        DirectoryStream.Filter<Path> filter = PathFilter.largerThan(10_000);
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(DIRECTORY, filter)) {
            Iterator<Path> iterator = paths.iterator();
            Assertions.assertEquals("bmp-801kb.bmp", iterator.next().getFileName().toString());
            Assertions.assertFalse(iterator.hasNext());
        }
    }

    @Test
    public void lessThanFilterTest() throws IOException {
        DirectoryStream.Filter<Path> filter = PathFilter.lessThan(2000);
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(DIRECTORY, filter)) {
            Iterator<Path> iterator = paths.iterator();
            Assertions.assertEquals("TestFile.txt", iterator.next().getFileName().toString());
            Assertions.assertFalse(iterator.hasNext());
        }
    }

    @Test
    public void matchesFilterTest() throws IOException {
        DirectoryStream.Filter<Path> filter = PathFilter.matches(".*[-]8.*");
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(DIRECTORY, filter)) {
            Iterator<Path> iterator = paths.iterator();
            Assertions.assertEquals("bmp-801kb.bmp", iterator.next().getFileName().toString());
            Assertions.assertFalse(iterator.hasNext());
        }
    }

    @Test
    public void writableAndReadableFilterTest() throws IOException {
        DirectoryStream.Filter<Path> filter = PathFilter.READABLE.and(PathFilter.WRITABLE);
        try (DirectoryStream<Path> filtered = Files.newDirectoryStream(DIRECTORY, filter);
             DirectoryStream<Path> notFiltered = Files.newDirectoryStream(DIRECTORY)) {
            Assertions.assertEquals(
                notFiltered.spliterator().estimateSize(),
                filtered.spliterator().estimateSize());
        }
    }

    @Test
    public void magicNumberFilterTest() throws IOException {
        MagicNumberFilter pngFilter = new MagicNumberFilter(0x89, 'P', 'N', 'G');
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(DIRECTORY, pngFilter)) {
            Iterator<Path> iterator = paths.iterator();
            Assertions.assertEquals("img.png", iterator.next().getFileName().toString());
            Assertions.assertFalse(iterator.hasNext());
        }
    }

    @Test
    public void globFilterEmptyTest() throws IOException {
        GlobMatchesFilter txtFilter = new GlobMatchesFilter("*.txt");
        GlobMatchesFilter pngFilter = new GlobMatchesFilter("*.png");
        DirectoryStream.Filter<Path> filterChain = txtFilter.and(pngFilter);
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(DIRECTORY, filterChain)) {
            Assertions.assertFalse(paths.iterator().hasNext());
        }
    }

    @Test
    public void globFilterTest() throws IOException {
        GlobMatchesFilter pngFilter = new GlobMatchesFilter("*.png");
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(DIRECTORY, pngFilter)) {
            Iterator<Path> iterator = paths.iterator();
            Assertions.assertEquals("img.png", iterator.next().getFileName().toString());
            Assertions.assertFalse(iterator.hasNext());
        }
    }

    @Test
    public void complexFilterTest() throws IOException {
        DirectoryStream.Filter<Path> filter =
            PathFilter.WRITABLE
            .and(PathFilter.READABLE)
            .and(PathFilter.largerThan(10))
            .and(new GlobMatchesFilter("*.png"))
            .and(new MagicNumberFilter(0x89, 'P', 'N', 'G'))
            .and(PathFilter.matches(".*i.*"));

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(
            DIRECTORY,
            filter
        )) {
            Iterator<Path> iterator = paths.iterator();
            Assertions.assertEquals("img.png", iterator.next().getFileName().toString());
            Assertions.assertFalse(iterator.hasNext());
        }
    }
}
