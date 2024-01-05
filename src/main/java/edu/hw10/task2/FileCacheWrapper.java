package edu.hw10.task2;

import edu.hw6.FilePathChecker;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FileCacheWrapper implements CacheWrapper {
    private Map<String, Object> cacheMap = new HashMap<>();
    private final Path path;

    private FileCacheWrapper(Path path) {
        this.path = path;
    }

    public static FileCacheWrapper create(Path path) throws NoSuchFileException {
        FilePathChecker.checkPath(path, false);
        return new FileCacheWrapper(path);
    }

    @Override
    public Object get(String key) {
        return cacheMap.get(key);
    }

    @Override
    public void put(String key, Object value) {
        cacheMap.put(key, value);
        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> getCache() {
        return Collections.unmodifiableMap(cacheMap);
    }

    private void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE))) {
            oos.writeObject(cacheMap);
        }
    }

    public static FileCacheWrapper load(Path path) throws IOException, ClassNotFoundException {
        FilePathChecker.checkPath(path, true);
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            var obj = ois.readObject();
            if (obj instanceof Map) {
                FileCacheWrapper wrapper = new FileCacheWrapper(path);
                wrapper.cacheMap = (Map<String, Object>) obj;
                return wrapper;
            }
        }
        return null;
    }
}
