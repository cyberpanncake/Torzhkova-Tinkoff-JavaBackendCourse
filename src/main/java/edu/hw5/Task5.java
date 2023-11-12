package edu.hw5;

public class Task5 {
    private Task5() {
    }

    public static boolean checkCarNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Строка не должна быть null");
        }
        return number.matches("[А-Я]\\d{3}[А-Я]{2}\\d{3}");
    }
}
