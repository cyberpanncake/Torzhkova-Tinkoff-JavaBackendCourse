package edu.hw5;

public class Task8 {
    private static final String NULL_STRING = "Строка не может быть null";

    private Task8() {
    }

    // нечётной длины
    public static boolean check1(String string) {
        if (string == null) {
            throw new IllegalArgumentException(NULL_STRING);
        }
        return string.matches("([01]{2})*[01]");
    }

    // начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static boolean check2(String string) {
        if (string == null) {
            throw new IllegalArgumentException(NULL_STRING);
        }
        return string.matches("0([01]{2})*|1([01]{2})*[01]");
    }

    // каждый нечетный символ равен 1
    public static boolean check5(String string) {
        if (string == null) {
            throw new IllegalArgumentException(NULL_STRING);
        }
        return string.matches("(1[01])*1?");
    }

    // нет последовательных 1
    public static boolean check7(String string) {
        if (string == null) {
            throw new IllegalArgumentException(NULL_STRING);
        }
        return string.matches("1?(0+1)*0*");
    }
}
