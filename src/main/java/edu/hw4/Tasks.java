package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tasks {

    private Tasks() {
    }

    public static List<Animal> task1(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> task2(List<Animal> animals, int k) {
        checkAnimals(animals);
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k).toList();
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt((x) -> 1)));
    }

    public static Animal task4(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream().max(Comparator.comparingInt((Animal a) -> a.name().length())).orElse(null);
    }

    public static Animal.Sex task5(List<Animal> animals) {
        checkAnimals(animals);
        try {
            return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.summingInt((x) -> 1)))
                .entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).orElse(null).getKey();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        checkAnimals(animals);
        return animals.stream().collect(Collectors.toMap(Animal::type, Function.identity(),
            BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))));
    }

    public static Animal task7(List<Animal> animals, int k) {
        checkAnimals(animals);
        List<Animal> sorted = animals.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).toList();
        try {
            return sorted.get(k);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static void checkAnimals(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не должен быть null");
        }
    }
}
