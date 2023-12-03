package edu.hw8;

import edu.hw8.task3.AbstractPasswordCracker;
import edu.hw8.task3.ConcurrentPasswordCracker;
import edu.hw8.task3.PasswordCracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Map;
import java.util.stream.Stream;

class Task3Test {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final Map<String, String> passwords = Map.of(
        "c4ca4238a0b923820dcc509a6f75849b", "a.o.torzhkova",
        "900150983cd24fb0d6963f7d28e17f72", "i.b.antonov",
        "827ccb0eea8a706c4c34a16891f84e7b", "k.g.sharikov",
        "3116a8624325337044f8e45837b71331", "a.a.almazov",
        "d3eb9a9233e52948740d7eb8c3062d14", "b.b.baranov"
    );
    private static final Map<String, String> expected = Map.of(
        "a.o.torzhkova", "1",
        "i.b.antonov", "abc",
        "k.g.sharikov", "12345",
        "a.a.almazov", "psswd",
        "b.b.baranov", "99999"
    );

    @ParameterizedTest
    @MethodSource("crackers")
    void crackTest(AbstractPasswordCracker cracker) {
        Map<String, String> actual = cracker.crack();
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> crackers() {
        return Stream.of(
            Arguments.of(new PasswordCracker(passwords)),
            Arguments.of(new ConcurrentPasswordCracker(passwords))
        );
    }

    @Test
    void crackPerformanceTest() {
        StringBuilder performance = new StringBuilder();

        long time = System.nanoTime();
        Map<String, String> actual = new PasswordCracker(passwords).crack();
        time = System.nanoTime() - time;
        double time1 = time;
        Assertions.assertEquals(expected, actual);
        performance.append("\nОднопоточная:\n").append(timeToString(time));

        long timeSum = 0;
        int[] threadsCount = new int[] { 2, 4, 8 };
        for (int t : threadsCount) {
            time = System.nanoTime();
            actual = new ConcurrentPasswordCracker(passwords, t).crack();
            time = System.nanoTime() - time;
            timeSum += time;
            Assertions.assertEquals(expected, actual);
            performance.append("\nМногопоточная (кол-во потоков %d):\n".formatted(t)).append(timeToString(time));
        }

        performance.append("\nСреднее ускорение решения: %f\n"
            .formatted(time1 / (timeSum / (double) threadsCount.length)));
        LOGGER.info(performance.toString());
    }

    private String timeToString(long time) {
        return "Время работы: %d с %d мс %d мкс %d нс\n"
            .formatted(time / 1_000_000_000, time / 1_000_000 % 1_000, time / 1_000 % 1_000, time % 1_000);
    }

    @Test
    void passwordCrackerCreationExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new PasswordCracker(null));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new ConcurrentPasswordCracker(null));
    }

    @Test
    void passwordCrackerCreationThreadCountExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new ConcurrentPasswordCracker(passwords, 0));
    }
}
