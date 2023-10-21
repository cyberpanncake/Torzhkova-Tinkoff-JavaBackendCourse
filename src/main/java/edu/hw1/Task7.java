package edu.hw1;

public class Task7 {

    public int rotateLeft(int n, int shift) {
        return rotate(n, shift, true);
    }

    public int rotateRight(int n, int shift) {
        return rotate(n, shift, false);
    }

    private int rotate(int n, int shift, boolean left) {
        if (n < 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        if (shift < 0) {
            throw new IllegalArgumentException("Сдвиг должен быть положительным");
        }
        String binary = Integer.toBinaryString(n);
        int actualShift = shift % binary.length();
        int divideIndex = left ? actualShift : binary.length() - actualShift;
        String leftPart = binary.substring(0, divideIndex);
        String rightPart = binary.substring(binary.length() - divideIndex);
        return Integer.parseInt(rightPart + leftPart, 2);
    }
}
