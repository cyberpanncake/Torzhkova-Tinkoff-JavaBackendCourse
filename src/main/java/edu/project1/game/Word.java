package edu.project1.game;

import edu.project1.throwable.CreateWordException;

public class Word {
    private static final String WRONG_WORD_LENGTH_ERROR_MESSAGE = "Ошибка при создании угадываемого слова."
        + "Слово должно иметь больше 2-х букв";
    private static final String UNGUESSED_LETTER = "*";
    private final char[] letters;
    private final boolean[] isGuessed;
    private final int length;

    public Word(String word) throws CreateWordException {
        if (word == null || word.length() <= 2) {
            throw new CreateWordException(WRONG_WORD_LENGTH_ERROR_MESSAGE);
        }
        letters = word.toLowerCase().toCharArray();
        length = letters.length;
        isGuessed = new boolean[length];
        for (int i = 0; i < length; i++) {
            isGuessed[i] = false;
        }
    }

    public String getView() {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length; i++) {
            word.append(isGuessed[i] ? letters[i] : UNGUESSED_LETTER);
        }
        return word.toString();
    }

    public boolean isAllLettersGuessed() {
        for (int i = 0; i < length; i++) {
            if (!isGuessed[i]) {
                return false;
            }
        }
        return true;
    }

    public int getLength() {
        return length;
    }

    public boolean guessLetter(char letter) {
        boolean isLetterGuessed = false;
        for (int i = 0; i < length; i++) {
            if (letters[i] == letter && !isGuessed[i]) {
                isGuessed[i] = true;
                isLetterGuessed = true;
            }
        }
        return isLetterGuessed;
    }
}
