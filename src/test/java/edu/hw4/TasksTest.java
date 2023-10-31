package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class TasksTest {
    private static Animal[] a;

    @BeforeAll
    public static void fillAnimals() {
        a = new Animal[] {
            new Animal("Мурка", Animal.Type.CAT, Animal.Sex.F, 3, 40, 5000, false),
            new Animal("Шарик", Animal.Type.DOG, Animal.Sex.M, 8, 54, 10000, true),
            new Animal("Аква", Animal.Type.FISH, Animal.Sex.F, 150, 1, 1, true),
            new Animal("Man", Animal.Type.SPIDER, Animal.Sex.M, 22, 5, 37, true),
            new Animal("Барбос", Animal.Type.DOG, Animal.Sex.M, 9, 100, 25000, false),
            new Animal("Муха", Animal.Type.BIRD, Animal.Sex.F, 4, 18, 500, false),
            new Animal("Кеша", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 650, false)
        };
    }

    @ParameterizedTest
    @MethodSource("parametersTask1")
    public void task1Test(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = Tasks.task1(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask1() {
        return Stream.of(
            Arguments.of(List.of(a), List.of(a[2], a[3], a[5], a[6], a[0], a[1], a[4])),
            Arguments.of(List.of(), List.of())
        );
    }

    @Test
    public void task1ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task1(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask2")
    public void task2Test(List<Animal> animals, int k, List<Animal> expected) {
        List<Animal> actual = Tasks.task2(animals, k);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask2() {
        return Stream.of(
            Arguments.of(List.of(a), 1, List.of(a[4])),
            Arguments.of(List.of(a), 3, List.of(a[4], a[1], a[0])),
            Arguments.of(List.of(a), 100, List.of(a[4], a[1], a[0], a[6], a[5], a[3], a[2])),
            Arguments.of(List.of(), 1, List.of())
        );
    }

    @Test
    public void task2ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task2(null, 1));
    }

    @ParameterizedTest
    @MethodSource("parametersTask3")
    public void task3Test(List<Animal> animals, Map<Animal.Type, Integer> expected) {
        Map<Animal.Type, Integer> actual = Tasks.task3(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask3() {
        return Stream.of(
            Arguments.of(List.of(a), Map.of(Animal.Type.DOG, 2,
                                            Animal.Type.CAT, 1,
                                            Animal.Type.BIRD, 2,
                                            Animal.Type.SPIDER, 1,
                                            Animal.Type.FISH, 1)),
            Arguments.of(List.of(), Map.of())
        );
    }

    @Test
    public void task3ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task3(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask4")
    public void task4Test(List<Animal> animals, Animal expected) {
        Animal actual = Tasks.task4(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask4() {
        return Stream.of(
            Arguments.of(List.of(a), a[4]),
            Arguments.of(List.of(), null)
        );
    }

    @Test
    public void task4ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task4(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask5")
    public void task5Test(List<Animal> animals, Animal.Sex expected) {
        Animal.Sex actual = Tasks.task5(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask5() {
        return Stream.of(
            Arguments.of(List.of(a), Animal.Sex.M),
            Arguments.of(List.of(), null)
        );
    }

    @Test
    public void task5ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task5(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask6")
    public void task6Test(List<Animal> animals, Map<Animal.Type, Animal> expected) {
        Map<Animal.Type, Animal> actual = Tasks.task6(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask6() {
        return Stream.of(
            Arguments.of(List.of(a), Map.of(Animal.Type.DOG, a[4],
                                            Animal.Type.CAT, a[0],
                                            Animal.Type.BIRD, a[6],
                                            Animal.Type.SPIDER, a[3],
                                            Animal.Type.FISH, a[2])),
            Arguments.of(List.of(), Map.of())
        );
    }

    @Test
    public void task6ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task6(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask7")
    public void task7Test(List<Animal> animals, int k, Animal expected) {
        Animal actual = Tasks.task7(animals, k);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask7() {
        return Stream.of(
            Arguments.of(List.of(a), 0, a[2]),
            Arguments.of(List.of(a), 1, a[3]),
            Arguments.of(List.of(a), 100, null),
            Arguments.of(List.of(), 1, null)
        );
    }

    @Test
    public void task7ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task7(null, 1));
    }
}
