package edu.hw5;

public class Task5 {
    private Task5() {
    }

    public static boolean checkCarNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Строка не должна быть null");
        }
        return number.matches("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");
    }
}
