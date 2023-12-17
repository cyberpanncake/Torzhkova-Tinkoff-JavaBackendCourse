package edu.hw10.task2;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class CacheProxyHandler implements InvocationHandler {
    private static Path cachePath = null;
    private final CacheWrapper runtimeCache = new RuntimeCacheWrapper();
    private final CacheWrapper fileCache;
    private final Object object;

    private CacheProxyHandler(Object object, FileCacheWrapper fileCache) {
        this.object = object;
        this.fileCache = fileCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation == null) {
            return method.invoke(object, args);
        }
        CacheWrapper cache = cacheAnnotation.persist() ? fileCache : runtimeCache;
        String key = getMethodKey(method, args);
        Object cachedResult = cache.get(key);
        if (cachedResult != null) {
            return cachedResult;
        }
        Object result = method.invoke(object, args);
        cache.put(key, result);
        return result;
    }

    private String getMethodKey(Method method, Object[] args) {
        String argsString = String.join(", ", Arrays.stream(args)
            .map(Object::toString)
            .toList());
        return "%s(%s)".formatted(method.getName(), argsString);
    }

    public static CacheProxyHandler create(Object target) throws IOException {
        Path currentPath = Paths.get(cachePath.toString());
        FileCacheWrapper diskStorage = FileCacheWrapper.create(currentPath);
        return new CacheProxyHandler(target, diskStorage);
    }

    public static void setCachePath(Path newPath) {
        cachePath = newPath;
    }
}
