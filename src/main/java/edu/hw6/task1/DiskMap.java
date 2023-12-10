package edu.hw6.task1;

import edu.hw6.FilePathChecker;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record DiskMap(Map<String, String> map, Path path) implements Map<String, String> {
    private static final String NULL_KEY_MESSAGE = "Ключ не должен быть пустым";
//    private final Map<String, String> map;
//    private final Path path;
    private static final String DELIMITER = ":";

//    private DiskMap(Path path, Map<String, String> map) {
//        this.path = path;
//        this.map = map;
//    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        checkKey(key);
        String result = map.put(key, value);
        write();
        return result;
    }

    @Override
    public String remove(Object key) {
        String result = map.remove(key);
        if (result != null) {
            write();
        }
        return result;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        m.forEach((key, value) -> checkKey(key));
        map.putAll(m);
        write();
    }

    @Override
    public void clear() {
        map.clear();
        write();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public static DiskMap read(Path path) throws IOException {
        FilePathChecker.checkPath(path);
        try (var streamLines = Files.lines(path)) {
            Map<String, String> map = streamLines
                .map(string -> {
                    String[] splitString = string.strip().split(DELIMITER);
                    return new AbstractMap.SimpleEntry<>(splitString[0], splitString[1]);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
            return new DiskMap(map, path);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DiskMapException("Неверный формат файла");
        }

    }

    public void write(Path path) throws NoSuchFileException {
        FilePathChecker.checkPath(path, false);
        try {
            Files.writeString(path, convertToString());
        } catch (IOException e) {
            throw new DiskMapException(e.getMessage());
        }
    }

    private void write() {
        try {
            Files.writeString(path, convertToString());
        } catch (IOException e) {
            throw new DiskMapException(e.getMessage());
        }
    }

    private String convertToString() {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : map.entrySet()) {
            result.append("%s%s%s\n".formatted(entry.getKey(), DELIMITER, entry.getValue()));
        }
        return result.toString();
    }

    private static void checkKey(Object key) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_MESSAGE);
        }
    }
}
