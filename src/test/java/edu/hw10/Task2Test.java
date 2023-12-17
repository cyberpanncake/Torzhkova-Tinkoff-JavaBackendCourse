package edu.hw10;

import edu.hw10.task2.Cache;
import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.CacheProxyHandler;
import edu.hw10.task2.FileCacheWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

class Task2Test {
    private static final String FILE_NAME = "cache.txt";
    @TempDir
    private static Path tempDir;

    @BeforeAll
    static void setCacheProxyHandlerPath() {
        CacheProxyHandler.setCachePath(tempDir.resolve(FILE_NAME));
    }

    public interface FibCalculator {
        int fib(int n);
        int countInvokes();
        default int fibResursive(int n) {
            if (n == 1 || n == 2) {
                return 1;
            } else {
                return fibResursive(n - 1) + fibResursive(n - 2);
            }
        }
    }

    public interface FileCachedFibCalculator extends FibCalculator {
        @Cache(persist = true)
        int fib(int n);
    }

    public interface RuntimeCachedFibCalculator extends FibCalculator {
        @Cache()
        int fib(int n);
    }

    public static class FileCachedFibCalculatorImpl implements FileCachedFibCalculator {
        int invokes = 0;
        @Override
        public int fib(int n) {
            invokes++;
            return fibResursive(n);
        }

        @Override
        public int countInvokes() {
            return invokes;
        }
    }

    public static class RuntimeCachedFibCalculatorImpl implements RuntimeCachedFibCalculator {
        int invokes = 0;
        @Override
        public int fib(int n) {
            invokes++;
            return fibResursive(n);
        }

        @Override
        public int countInvokes() {
            return invokes;
        }
    }

    public static class FibCalculatorImpl implements FibCalculator {
        int invokes = 0;
        @Override
        public int fib(int n) {
            invokes++;
            return fibResursive(n);
        }

        @Override
        public int countInvokes() {
            return invokes;
        }
    }

    @ParameterizedTest
    @MethodSource("params")
    @SuppressWarnings("MagicNumber")
    void proxyTest(Supplier<FibCalculator> constructor, int expected) throws IOException {
        FibCalculator calculator = constructor.get();
        FibCalculator proxy = (FibCalculator) CacheProxy.create(calculator);
        for (int i = 0; i < 10; i++) {
            proxy.fib(1);
        }
        Assertions.assertEquals(expected, proxy.countInvokes());
    }

    @SuppressWarnings("MagicNumber")
    private static Arguments[] params() {
        return new Arguments[] {
            Arguments.of((Supplier<FibCalculator>) FibCalculatorImpl::new, 10),
            Arguments.of((Supplier<FibCalculator>) FileCachedFibCalculatorImpl::new, 1),
            Arguments.of((Supplier<FibCalculator>) RuntimeCachedFibCalculatorImpl::new, 1)
        };
    }

    @Test
    void fileCacheProxyTest() throws IOException, ClassNotFoundException {
        FileCachedFibCalculatorImpl calculator = new FileCachedFibCalculatorImpl();
        FileCachedFibCalculator proxy = (FileCachedFibCalculator) CacheProxy.create(calculator);
        for (int i = 0; i < 10; i++) {
            proxy.fib(1);
        }
        FileCacheWrapper wrapper = FileCacheWrapper.load(tempDir.resolve(FILE_NAME));
        assert wrapper != null;
        Set<Map.Entry<String, Object>> cache = wrapper.getCache().entrySet();
        Map.Entry<String, Object> actual = cache.stream().toList().get(0);
        Assertions.assertEquals(1, calculator.countInvokes());
        Assertions.assertEquals(1, cache.size());
        Assertions.assertEquals("fib(1)", actual.getKey());
        Assertions.assertEquals(1, actual.getValue());
    }
}
