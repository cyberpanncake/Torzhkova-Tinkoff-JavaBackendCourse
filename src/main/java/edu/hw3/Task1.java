package edu.hw3;

public class Task1 {

    public String atbash(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Пустая входная строка");
        }
        char[] inputChars = input.toCharArray();
        char[] output = new char[inputChars.length];
        for (int i = 0; i < inputChars.length; i++) {
            if (inputChars[i] >= 'A' && inputChars[i] <= 'Z') {
                output[i] = (char) ('Z' - (inputChars[i] - 'A'));
            } else if (inputChars[i] >= 'a' && inputChars[i] <= 'z') {
                output[i] = (char) ('z' - (inputChars[i] - 'a'));
            } else {
                output[i] = inputChars[i];
            }
        }
        return String.valueOf(output);
    }
}
