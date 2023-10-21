package edu.hw1;

public class Task4 {

    public String fixString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Входная стркоа не может быть null");
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }
        return String.valueOf(chars);
    }
}
