package edu.hw5;

public class Task7 {
    private static final String NULL_STRING = "Строка не может быть null";

    private Task7() {
    }

    // содержит не менее 3 символов, причем третий символ равен 0
    public static boolean check1(String string) {
        if (string == null) {
            throw new IllegalArgumentException(NULL_STRING);
        }
        return string.matches("^[01]{2}0[01]*$");
    }

    // начинается и заканчивается одним и тем же символом
    public static boolean check2(String string) {
        if (string == null) {
            throw new IllegalArgumentException(NULL_STRING);
        }
        return string.matches("^([01])([01]*\\1$|$)");
    }

    // длина не менее 1 и не более 3
    public static boolean check3(String string) {
        if (string == null) {
            throw new IllegalArgumentException(NULL_STRING);
        }
        return string.matches("^[01]{1,3}$");
    }
}
