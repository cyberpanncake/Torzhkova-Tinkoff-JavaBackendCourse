package edu.hw4;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ValidationError(String field, String error) {

    public static Set<ValidationError> getErrors(Animal animal) {
        return Stream.of(checkName(animal),
                         checkAge(animal),
                         checkHeight(animal),
                         checkWeight(animal),
                         checkTypeCanByte(animal))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    private static ValidationError checkName(Animal animal) {
        ValidationError error = null;
        if (animal.name() == null || animal.name().isEmpty()) {
            error = new ValidationError("name", "Пустое имя");
        }
        return error;
    }

    private static ValidationError checkAge(Animal animal) {
        ValidationError error = null;
        if (animal.age() < 0) {
            error = new ValidationError("age", "Возраст меньше 0");
        }
        return error;
    }

    private static ValidationError checkHeight(Animal animal) {
        ValidationError error = null;
        if (animal.height() <= 0) {
            error = new ValidationError("height", "Рост не положительный");
        }
        return error;
    }

    private static ValidationError checkWeight(Animal animal) {
        ValidationError error = null;
        if (animal.weight() <= 0) {
            error = new ValidationError("weight", "Вес не положительный");
        }
        return error;
    }

    private static ValidationError checkTypeCanByte(Animal animal) {
        Set<Animal.Type> typesCanBite = Set.of(Animal.Type.DOG, Animal.Type.CAT, Animal.Type.SPIDER);
        ValidationError error = null;
        if (!typesCanBite.contains(animal.type()) && animal.bites()) {
            error = new ValidationError("type, bites", "Кусается, хотя не должно");
        }
        return error;
    }
}
