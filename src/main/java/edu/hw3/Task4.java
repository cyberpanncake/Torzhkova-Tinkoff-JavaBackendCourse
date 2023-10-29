package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public class Task4 {
    private final TreeMap<Integer, String> romanDigits = new TreeMap<>(Comparator.reverseOrder());

    @SuppressWarnings("MagicNumber")
    public Task4() {
        romanDigits.put(1000, "M");
        romanDigits.put(900, "CM");
        romanDigits.put(500, "D");
        romanDigits.put(400, "CD");
        romanDigits.put(100, "C");
        romanDigits.put(90, "XC");
        romanDigits.put(50, "L");
        romanDigits.put(40, "XL");
        romanDigits.put(10, "X");
        romanDigits.put(9, "IX");
        romanDigits.put(5, "V");
        romanDigits.put(4, "IV");
        romanDigits.put(1, "I");
    }

    public String convertToRoman(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        int number = n;
        StringBuilder result = new StringBuilder();
        for (int k : romanDigits.keySet()) {
            while (number >= k) {
                number -= k;
                result.append(romanDigits.get(k));
            }
        }
        return result.toString();
    }
}
