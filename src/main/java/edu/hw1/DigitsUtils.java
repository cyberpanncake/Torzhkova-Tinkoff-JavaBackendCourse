package edu.hw1;

import java.util.List;
import java.util.stream.Collectors;

public class DigitsUtils {
    private static final int NUMERAL_SYSTEM = 10;

    private DigitsUtils() {
    }

    public static List<Integer> numberToDigits(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        return String.valueOf(number).chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
    }

    public static int digitsToNumber(List<Integer> digits) {
        for (int digit : digits) {
            if (digit < 0) {
                throw new IllegalArgumentException("Цифра не может быть отрицательной");
            }
        }
        return digits.stream().reduce(0, (sum, digit) -> sum * NUMERAL_SYSTEM + digit);
    }
}
