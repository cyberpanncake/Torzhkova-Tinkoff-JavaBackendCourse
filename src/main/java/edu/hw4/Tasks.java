package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tasks {
    private static final String ANIMALS_NULL_MESSAGE = "Список животных не должен быть null";

    private Tasks() {
    }

    public static List<Animal> task1(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> task2(List<Animal> animals, int k) {
        checkAnimals(animals);
        checkCount(k);
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(a -> 1)));
    }

    public static Animal task4(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .max(Comparator.comparingInt(a -> a.name().length()))
            .orElse(null);
    }

    public static Animal.Sex task5(List<Animal> animals) {
        checkAnimals(animals);
        try {
            return animals.stream()
                .collect(Collectors.groupingBy(Animal::sex, Collectors.summingInt(a -> 1)))
                .entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .orElse(null)
                .getKey();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))));
    }

    public static Animal task7(List<Animal> animals, int k) {
        checkAnimals(animals);
        checkIndex(k);
        List<Animal> sorted = animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .toList();
        try {
            return sorted.get(k);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static Optional<Animal> task8(List<Animal> animals, int k) {
        checkAnimals(animals);
        checkIntCharacteristic(k);
        return animals.stream()
            .filter(a -> a.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> task10(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .filter(a -> a.paws() != a.age())
            .toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .filter(a -> a.bites() && a.height() > 100)
            .toList();
    }

    public static Integer task12(List<Animal> animals) {
        checkAnimals(animals);
        return Math.toIntExact(animals.stream()
            .filter(a -> a.weight() > a.height())
            .count());
    }

    public static List<Animal> task13(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .filter(a -> a.name().split(" ").length > 2)
            .toList();
    }

    public static Boolean task14(List<Animal> animals, int k) {
        checkAnimals(animals);
        checkIntCharacteristic(k);
        return animals.stream()
            .anyMatch(a -> a.type() == Animal.Type.DOG && a.height() > k);
    }

    public static int task15(List<Animal> animals, int k, int l) {
        checkAnimals(animals);
        checkIntCharacteristic(k);
        checkIntCharacteristic(l);
        checkDiapason(k, l);
        return animals.stream()
            .filter(a -> a.age() >= k && a.age() <= l)
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> task16(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static Boolean task17(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .filter(a -> a.type() == Animal.Type.DOG && a.bites())
            .count()
            < animals.stream()
            .filter(a -> a.type() == Animal.Type.SPIDER && a.bites())
            .count();
    }

    public static Animal task18(List<List<Animal>> animals) {
        if (animals == null) {
            throw new IllegalArgumentException(ANIMALS_NULL_MESSAGE);
        }
        for (List<Animal> group : animals) {
            checkAnimals(group);
        }
        return animals.stream()
            .map(as -> as.stream()
                .filter(a -> a.type() == Animal.Type.FISH)
                .max(Comparator.comparing(Animal::weight)))
            .max(Comparator.comparing(a -> a.map(Animal::weight).orElse(0)))
            .orElseGet(null).orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream()
            .collect(Collectors.toMap(Animal::name, ValidationError::getErrors));
    }

    public static Map<String, String> task20(List<Animal> animals) {
        checkAnimals(animals);
        return task19(animals).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, el -> "Ошибки:\n" + el.getValue().stream()
                .map(e -> e.field() + ": " + e.error())
                .collect(Collectors.joining("\n"))));
    }

    private static void checkAnimals(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException(ANIMALS_NULL_MESSAGE);
        }
    }

    private static void checkIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным");
        }
    }

    private static void checkCount(int c) {
        if (c < 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным");
        }
    }

    private static void checkIntCharacteristic(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Характеристика должна быть положительной");
        }
    }

    private static void checkDiapason(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Верхняя граница диапазона должна быть больше нижней");
        }
    }
}
