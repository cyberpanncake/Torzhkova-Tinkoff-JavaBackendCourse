package edu.hw1;

import java.util.Comparator;
import java.util.List;

public class Task6 {
    private static final Task2 TASK2 = new Task2();
    private static final int KAPREKARS_CONST = 6174;
    private static final int VALID_COUNT_DIGITS = 4;

    public int countK(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        if (TASK2.countDigits(n) != VALID_COUNT_DIGITS) {
            throw new IllegalArgumentException("Число должно быть 4-хразрядным");
        }
        List<Integer> digits = DigitsUtils.numberToDigits(n);
        digits.sort(Comparator.naturalOrder());
        if (digits.getFirst().equals(digits.getLast())) {
            throw new IllegalArgumentException("Число должно иметь хотя бы две разные цифры");
        }
        return countKRecursive(n);
    }

    private int countKRecursive(int n) {
        List<Integer> digits = DigitsUtils.numberToDigits(n);
        digits.sort(Comparator.naturalOrder());
        int nOrdered = DigitsUtils.digitsToNumber(digits);
        digits.sort(Comparator.reverseOrder());
        int nOrderedReverse = DigitsUtils.digitsToNumber(digits);
        int nNext = nOrderedReverse - nOrdered;
        if (nNext == KAPREKARS_CONST) {
            return 1;
        }
        return countKRecursive(nNext) + 1;
    }
}
