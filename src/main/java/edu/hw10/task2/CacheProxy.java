package edu.hw10.task2;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class CacheProxy {
    private CacheProxy() {}

    public static Object create(Object obj) throws IOException {
        Class<?> cl = obj.getClass();
        CacheProxyHandler handler = CacheProxyHandler.create(obj);
        return Proxy.newProxyInstance(cl.getClassLoader(), cl.getInterfaces(), handler);
    }
}
