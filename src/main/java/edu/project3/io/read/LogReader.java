package edu.project3.io.read;

import edu.project3.log.IllegalLogException;
import edu.project3.log.Log;
import edu.project3.log.LogRowMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LogReader {

    private LogReader() {
    }

    public static LogsData read(Config config) throws IOException, IllegalLogException {
        List<Log> logs;
        List<String> paths = new ArrayList<>();
        try {
            logs = new ArrayList<>(getLogStream(config, paths)
                .map(LogRowMapper::map)
                .filter(l -> {
                    LocalDateTime start = config.startDate();
                    LocalDateTime end = config.endDate();
                    return (l.date().isAfter(start) || l.date().isEqual(start))
                        && (l.date().isBefore(end) || l.date().isEqual(end));
                })
                .toList());
        } catch (IllegalLogException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException("Не удалось прочитать логи из файла(-ов)");
        }
        return new LogsData(logs, paths);
    }

    private static Stream<String> getLogStream(Config config, List<String> paths)
        throws URISyntaxException, IOException, InterruptedException {
        if (isURL(config.path())) {
            return getLogStreamFromUrl(config.path(), paths);
        } else {
            return getLogStreamFromGlob(config.path(), paths);
        }
    }

    private static boolean isURL(String path) {
        try {
            new URL(path);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    @SuppressWarnings("MagicNumber")
    public static Stream<String> getLogStreamFromUrl(String url, List<String> paths)
        throws URISyntaxException, IOException, InterruptedException {
        paths.add(url);
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException();
        }
        return Arrays.stream(response.body().split("\n"));
    }

    public static Stream<String> getLogStreamFromGlob(String glob, List<String> paths) throws IOException {
        List<Path> matches = new ArrayList<>();
        String pattern = "glob:" + glob;
        Path current = Paths.get("");
        FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher(pattern);
                if (matcher.matches(file)) {
                    matches.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(current, matcherVisitor);
        try {
            return matches.stream()
                .peek(p -> paths.add(p.getFileName().toString()))
                .flatMap(path -> {
                    try {
                        return Files.readAllLines(path).stream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (RuntimeException e) {
            throw new IOException();
        }
    }
}
