package edu.hw1;

import java.util.ArrayList;
import java.util.List;

public class Task5 {

    public boolean isPalindromeDescendant(int n) {
        return isPalindromeDescendantRecursive(DigitsUtils.numberToDigits(n));
    }

    private boolean isPalindromeDescendantRecursive(List<Integer> digits) {
        if (digits.size() == 1) {
            return false;
        }
        if (isPalindrome(digits)) {
            return true;
        }
        if (digits.size() % 2 == 1) {
            throw new IllegalArgumentException("Невозможно создать потомка из числа, нечётное количество цифр");
        }
        List<Integer> descendant = makeDescendant(digits);
        return isPalindromeDescendantRecursive(descendant);
    }

    private boolean isPalindrome(List<Integer> digits) {
        int n = digits.size();
        for (int i = 0; i < n / 2; i++) {
            if (!digits.get(i).equals(digits.get(n - i - 1))) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> makeDescendant(List<Integer> digits) {
        int n = digits.size();
        List<Integer> descendant = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            int sum = digits.get(i) + digits.get(i + 1);
            descendant.addAll(DigitsUtils.numberToDigits(sum));
        }
        return descendant;
    }
}
