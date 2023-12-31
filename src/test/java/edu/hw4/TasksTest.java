package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

class TasksTest {
    private static final Animal[] a = new Animal[] {
        new Animal("Мурка Павловна", Animal.Type.CAT, Animal.Sex.F, 4, 40, 5000, false),
        new Animal("Шарик", Animal.Type.DOG, Animal.Sex.M, 8, 100, 10000, false),
        new Animal("Аква Минерале Экстра", Animal.Type.FISH, Animal.Sex.F, 150, 1, 1, true),
        new Animal("Man", Animal.Type.SPIDER, Animal.Sex.M, 22, 5, 37, true),
        new Animal("Барбос", Animal.Type.DOG, Animal.Sex.M, 9, 150, 25000, true),
        new Animal("Муха - это маленькая птичка", Animal.Type.BIRD, Animal.Sex.F, 2, 18, 500, false),
        new Animal("Кеша", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 650, false),
        new Animal("Man", Animal.Type.SPIDER, Animal.Sex.M, 22, 5, 37, true)
    };

    @ParameterizedTest
    @MethodSource("parametersTask1")
    public void task1Test(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = Tasks.task1(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask1() {
        return Stream.of(
            Arguments.of(List.of(a), List.of(a[2], a[3], a[7], a[5], a[6], a[0], a[1], a[4])),
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
            Arguments.of(List.of(a), 100, List.of(a[4], a[1], a[0], a[6], a[5], a[3], a[7], a[2])),
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
                                            Animal.Type.SPIDER, 2,
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
            Arguments.of(List.of(a), a[5]),
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

    @ParameterizedTest
    @MethodSource("parametersTask8")
    public void task8Test(List<Animal> animals, int k, Optional<Animal> expected) {
        Optional<Animal> actual = Tasks.task8(animals, k);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask8() {
        return Stream.of(
            Arguments.of(List.of(a), 40, Optional.of(a[6])),
            Arguments.of(List.of(a), 15, Optional.of(a[3])),
            Arguments.of(List.of(a), 1, Optional.empty()),
            Arguments.of(List.of(), 1000, Optional.empty())
        );
    }

    @Test
    public void task8ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task8(null, 1));
    }

    @ParameterizedTest
    @MethodSource("parametersTask9")
    public void task9Test(List<Animal> animals, int expected) {
        int actual = Tasks.task9(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask9() {
        return Stream.of(
            Arguments.of(List.of(a), 32),
            Arguments.of(List.of(a[0]), 4),
            Arguments.of(List.of(), 0)
        );
    }

    @Test
    public void task9ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task9(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask10")
    public void task10Test(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = Tasks.task10(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask10() {
        return Stream.of(
            Arguments.of(List.of(a), List.of(a[1], a[2], a[3], a[4], a[6], a[7])),
            Arguments.of(List.of(), List.of())
        );
    }

    @Test
    public void task10ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task10(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask11")
    public void task11Test(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = Tasks.task11(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask11() {
        return Stream.of(
            Arguments.of(List.of(a), List.of(a[4])),
            Arguments.of(List.of(), List.of())
        );
    }

    @Test
    public void task11ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task11(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask12")
    public void task12Test(List<Animal> animals, int expected) {
        int actual = Tasks.task12(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask12() {
        return Stream.of(
            Arguments.of(List.of(a), 7),
            Arguments.of(List.of(), 0)
        );
    }

    @Test
    public void task12ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task12(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask13")
    public void task13Test(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = Tasks.task13(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask13() {
        return Stream.of(
            Arguments.of(List.of(a), List.of(a[2], a[5])),
            Arguments.of(List.of(), List.of())
        );
    }

    @Test
    public void task13ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task13(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask14")
    public void task14Test(List<Animal> animals, int k, boolean expected) {
        boolean actual = Tasks.task14(animals, k);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask14() {
        return Stream.of(
            Arguments.of(List.of(a), 100, true),
            Arguments.of(List.of(a), 160, false),
            Arguments.of(List.of(), 1, false)
        );
    }

    @Test
    public void task14ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task14(null, 1));
    }

    @ParameterizedTest
    @MethodSource("parametersTask15")
    public void task15Test(List<Animal> animals, int k, int l, Map<Animal.Type, Integer> expected) {
        Map<Animal.Type, Integer> actual = Tasks.task15(animals, k, l);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask15() {
        return Stream.of(
            Arguments.of(List.of(a), 2, 150, Map.of(Animal.Type.CAT, 5000,
                                                              Animal.Type.DOG, 35000,
                                                              Animal.Type.SPIDER, 74,
                                                              Animal.Type.FISH, 1,
                                                              Animal.Type.BIRD, 1150)),
            Arguments.of(List.of(a), 8, 22, Map.of(Animal.Type.DOG, 35000,
                                                             Animal.Type.SPIDER, 74)),
            Arguments.of(List.of(), 2, 150,  Map.of())
        );
    }

    @Test
    public void task15ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task15(null, 1, 2));
    }

    @ParameterizedTest
    @MethodSource("parametersTask16")
    public void task16Test(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = Tasks.task16(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask16() {
        return Stream.of(
            Arguments.of(List.of(a), List.of(a[0], a[4], a[1], a[6], a[5], a[2], a[3], a[7])),
            Arguments.of(List.of(), List.of())
        );
    }

    @Test
    public void task16ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task16(null));
    }

    @ParameterizedTest
    @MethodSource("parametersTask17")
    public void task17Test(List<Animal> animals, boolean expected) {
        boolean actual = Tasks.task17(animals);
        Assertions.assertEquals(expected,actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersTask17() {
        return Stream.of(
            Arguments.of(List.of(a), true),
            Arguments.of(List.of(), false)
        );
    }

    @Test
    public void task17ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task17(null));
    }

    @Test
    public void task18Test() {
        Animal fish100 = new Animal("", Animal.Type.FISH, Animal.Sex.M, 0, 0, 100, false);
        Animal fish200 = new Animal("", Animal.Type.FISH, Animal.Sex.M, 0, 0, 200, false);
        Animal fish500 = new Animal("", Animal.Type.FISH, Animal.Sex.M, 0, 0, 500, false);
        Animal other = new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false);
        List<List<Animal>> animals = List.of(List.of(fish200, other, fish100, fish100, other),
                                             List.of(),
                                             List.of(other, other, fish200, fish500),
                                             List.of(other, other, fish100));
        Animal expected = fish500;
        Animal actual = Tasks.task18(animals);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void task18NullTest() {
        Animal other = new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false);
        List<List<Animal>> animals = List.of(List.of(other, other),
                                             List.of(other));
        Animal actual = Tasks.task18(animals);
        Assertions.assertNull(actual);
    }

    @Test
    public void task18ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task18(null));
    }

    private static final Animal[] aError = new Animal[] {
        new Animal("", Animal.Type.CAT, Animal.Sex.F, 4, 40, 5000, false),
        new Animal("Шарик", Animal.Type.DOG, Animal.Sex.M, -1, 100, 10000, false),
        new Animal("Аква Минерале Экстра", Animal.Type.FISH, Animal.Sex.F, 150, 0, 1, true),
        new Animal("Man", Animal.Type.SPIDER, Animal.Sex.M, 22, 5, 0, true),
        new Animal("Муха - это маленькая птичка", Animal.Type.BIRD, Animal.Sex.F, 2, 18, 500, true),
        new Animal("Bug", Animal.Type.FISH, Animal.Sex.M, -1, 0, 0, true)
    };
    private static final ValidationError nameError = new ValidationError("name", "Пустое имя");
    private static final ValidationError ageError = new ValidationError("age", "Возраст меньше 0");
    private static final ValidationError heightError = new ValidationError("height", "Рост не положительный");
    private static final ValidationError weightError = new ValidationError("weight", "Вес не положительный");
    private static final ValidationError typeCannotBiteError = new ValidationError("type, bites", "Кусается, хотя не должно");

    @Test
    public void task19Test() {
        List<Animal> animals = List.of(aError);
        Map<String, Set<ValidationError>> expected = Map.of(
            "", Set.of(nameError),
            "Шарик", Set.of(ageError),
            "Аква Минерале Экстра", Set.of(heightError, typeCannotBiteError),
            "Man", Set.of(weightError),
            "Муха - это маленькая птичка", Set.of(typeCannotBiteError),
            "Bug", Set.of(ageError, heightError, weightError, typeCannotBiteError));
        Map<String, Set<ValidationError>> actual = Tasks.task19(animals);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void task19ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task19(null));
    }

    @Test
    public void task20Test() {
        List<Animal> animals = List.of(aError);
        Map<String, String> expected = Map.of(
            "", getErrorMessage(nameError),
            "Шарик", getErrorMessage(ageError),
            "Аква Минерале Экстра", getErrorMessage(heightError, typeCannotBiteError),
            "Man", getErrorMessage(weightError),
            "Муха - это маленькая птичка", getErrorMessage(typeCannotBiteError),
            "Bug", getErrorMessage(ageError, weightError, heightError, typeCannotBiteError));
        Map<String, String> actual = Tasks.task20(animals);
        Assertions.assertEquals(expected,actual);
    }

    private static String getErrorMessage(ValidationError... errors) {
        StringBuilder message = new StringBuilder("Ошибки:");
        for (ValidationError e : errors) {
            message.append("\n").append(e.field()).append(": ").append(e.error());
        }
        return message.toString();
    }

    @Test
    public void task20ExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.task20(null));
    }
}
