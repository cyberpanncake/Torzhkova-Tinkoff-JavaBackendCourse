package edu.hw5;

public class Task4 {
    private Task4() {
    }

    public static boolean checkPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Строка не должна быть null");
        }
        return password.matches(".*[~!@#$%^&*|].*");
    }
}
