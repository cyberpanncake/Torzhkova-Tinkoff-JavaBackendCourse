package edu.project1;

import edu.project1.game.Word;
import edu.project1.throwable.CreateWordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class WordTest {
    private static final String HIDDEN_LETTER = Word.HIDDEN_LETTER;
    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> parametersWords() {
        return Stream.of(
            Arguments.of("слово"),
            Arguments.of("два"),
            Arguments.of("водоснабжение"),
            Arguments.of("абстрактный")
        );
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void wordCreationLengthTest(String word) throws CreateWordException {
        int expected = word.length();
        Word wordObj = new Word(word);
        int actual = wordObj.getLength();
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void wordCreationViewTest(String word) throws CreateWordException {
        String expected = HIDDEN_LETTER.repeat(word.length());
        Word wordObj = new Word(word);
        String actual = wordObj.getView();
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void wordCreationGuessedTest(String word) throws CreateWordException {
        boolean expected = false;
        Word wordObj = new Word(word);
        boolean actual = wordObj.isAllLettersGuessed();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void wordCreationInvalidLengthTest() {
        String word = "я";
        Assertions.assertThrows(CreateWordException.class, () -> new Word(word));
    }

    @Test
    public void wordCreationInvalidLettersTest() {
        String word = "abc1";
        Assertions.assertThrows(CreateWordException.class, () -> new Word(word));
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void wordGuessFirstLetterTest(String word) throws CreateWordException {
        char firstLetter = word.charAt(0);
        boolean expected = true;
        Word wordObj = new Word(word);
        boolean actual = wordObj.guessLetter(firstLetter);
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void wordGuessFirstLetterViewTest(String word) throws CreateWordException {
        char firstLetter = word.charAt(0);
        String regex = "[^" + firstLetter + "]";
        String expected = word.replaceAll(regex, HIDDEN_LETTER);
        Word wordObj = new Word(word);
        wordObj.guessLetter(firstLetter);
        String actual = wordObj.getView();
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void wordNotGuessLetterTest(String word) throws CreateWordException {
        char letter = getLetterNotInWord(word);
        boolean expected = false;
        Word wordObj = new Word(word);
        boolean actual = wordObj.guessLetter(letter);
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void wordNotGuessLetterViewTest(String word) throws CreateWordException {
        char letter = getLetterNotInWord(word);
        String expected = HIDDEN_LETTER.repeat(word.length());
        Word wordObj = new Word(word);
        wordObj.guessLetter(letter);
        String actual = wordObj.getView();
        Assertions.assertEquals(expected,actual);
    }

    private char getLetterNotInWord(String word) {
        char letter = 'а';
        while (word.contains(String.valueOf(letter))) {
            letter++;
        }
        return letter;
    }
}
