package edu.hw1;

public class Task2 {
    private static final int NUMBER_SYSTEM = 10;

    public int countDigits(int n) {
        int nAbs = Math.abs(n);
        if (nAbs < NUMBER_SYSTEM) {
            return 1;
        }
        return (int) Math.log10(nAbs) + 1;
    }
}
