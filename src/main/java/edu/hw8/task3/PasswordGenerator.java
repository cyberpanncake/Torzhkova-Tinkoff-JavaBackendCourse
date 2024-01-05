package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {
    private static final List<Character> ALPHABET;
    public static final int ALPHABET_SIZE;
    private int value;
    private PasswordGenerator nextGenerator;

    static {
        ALPHABET = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            ALPHABET.add(ch);
        }
        for (char ch = '0'; ch <= '9'; ch++) {
            ALPHABET.add(ch);
        }
        ALPHABET_SIZE = ALPHABET.size();
    }

    public PasswordGenerator() {
        value = -1;
    }

    public PasswordGenerator(long initState) {
        long i = initState - 1;
        value = (int) (i % ALPHABET_SIZE);
        long nextState = i / ALPHABET_SIZE;
        if (nextState > 0) {
            nextGenerator = new PasswordGenerator(nextState);
        }
    }

    public String next() {
        if (++value == ALPHABET_SIZE) {
            value = 0;
            if (nextGenerator == null) {
                nextGenerator = new PasswordGenerator();
            }
            nextGenerator.next();
        }
        return convertToString();
    }

    private String convertToString() {
        return (nextGenerator == null ? "" : nextGenerator.convertToString()) + ALPHABET.get(value);
    }
}
