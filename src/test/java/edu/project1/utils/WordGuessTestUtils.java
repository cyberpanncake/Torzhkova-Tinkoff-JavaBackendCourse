package edu.project1.utils;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class WordGuessTestUtils {
    private char letter = 'а';

    @SuppressWarnings("MagicNumber")
    public static Stream<Arguments> parametersWords() {
        return Stream.of(
            Arguments.of("слово"),
            Arguments.of("два"),
            Arguments.of("водоснабжение"),
            Arguments.of("абстрактный"),
            Arguments.of("навигация")
        );
    }

    public char getNextLetterNotInWord(String word) {
        while (word.contains(String.valueOf(letter))) {
            letter++;
        }
        return letter++;
    }
}
