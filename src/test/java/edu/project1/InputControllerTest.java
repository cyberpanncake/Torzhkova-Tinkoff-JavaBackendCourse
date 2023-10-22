package edu.project1;

import edu.project1.game.InputController;
import edu.project1.game.Word;
import edu.project1.throwable.CreateWordException;
import edu.project1.throwable.NeedToStopGameEvent;
import edu.project1.throwable.WrongInputException;
import edu.project1.utils.WordGuessTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class InputControllerTest {
    private final WordGuessTestUtils wordGuessTestUtils = new WordGuessTestUtils();

    private static Stream<Arguments> parametersWords() {
        return WordGuessTestUtils.parametersWords();
    }

    private char getNextLetterNotInWord(String word) {
        return wordGuessTestUtils.getNextLetterNotInWord(word);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void processGuessFirstLetterTest(String word)
        throws CreateWordException, NeedToStopGameEvent, IOException, WrongInputException {
        char firstLetter = word.charAt(0);
        boolean expected = true;
        Word wordObj = new Word(word);
        BufferedReader reader = new BufferedReader(new StringReader(String.valueOf(firstLetter)));
        InputController controller = new InputController(reader, wordObj);
        boolean actual = controller.processGuess();
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void processNotGuessLetterTest(String word)
        throws CreateWordException, NeedToStopGameEvent, IOException, WrongInputException {
        char letter = getNextLetterNotInWord(word);
        boolean expected = false;
        Word wordObj = new Word(word);
        BufferedReader reader = new BufferedReader(new StringReader(String.valueOf(letter)));
        InputController controller = new InputController(reader, wordObj);
        boolean actual = controller.processGuess();
        Assertions.assertEquals(expected,actual);
    }


    @ParameterizedTest
    @MethodSource("parametersWords")
    public void processGuessTotalWordTest(String word)
        throws CreateWordException, NeedToStopGameEvent, IOException, WrongInputException {
        Set<String> lettersSet = new HashSet<>(List.of(word.split("")));
        String letters = String.join("\n", lettersSet);
        boolean expected = true;
        Word wordObj = new Word(word);
        BufferedReader reader = new BufferedReader(new StringReader(letters));
        InputController controller = new InputController(reader, wordObj);
        for (int i = 0; i < lettersSet.size(); i++) {
            controller.processGuess();
        }
        boolean actual = wordObj.isAllLettersGuessed();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void processNeedToStopGameEventTest() throws CreateWordException {
        Word wordObj = new Word("слово");
        String input = String.join("\n", List.of(""));
        BufferedReader reader = new BufferedReader(new StringReader(input));
        InputController controller = new InputController(reader, wordObj);
        Assertions.assertThrows(NeedToStopGameEvent.class, controller::processGuess);
    }

    @Test
    public void processGuessEmptyInputExceptionTest() throws CreateWordException {
        Word wordObj = new Word("слово");
        String input = String.join("\n", List.of("\n"));
        BufferedReader reader = new BufferedReader(new StringReader(input));
        InputController controller = new InputController(reader, wordObj);
        Assertions.assertThrows(WrongInputException.class, controller::processGuess);
    }

    @Test
    public void processGuessManyCharsInputExceptionTest() throws CreateWordException {
        Word wordObj = new Word("слово");
        String input = String.join("\n", List.of("абв"));
        BufferedReader reader = new BufferedReader(new StringReader(input));
        InputController controller = new InputController(reader, wordObj);
        Assertions.assertThrows(WrongInputException.class, controller::processGuess);
    }

    @Test
    public void processGuessWrongCharInputExceptionTest() throws CreateWordException {
        Word wordObj = new Word("слово");
        String input = String.join("\n", List.of("1"));
        BufferedReader reader = new BufferedReader(new StringReader(input));
        InputController controller = new InputController(reader, wordObj);
        Assertions.assertThrows(WrongInputException.class, controller::processGuess);
    }

    @Test
    public void processGuessRepeatCharInputExceptionTest()
        throws CreateWordException, NeedToStopGameEvent, IOException, WrongInputException {
        Word wordObj = new Word("слово");
        String input = String.join("\n", List.of("о", "о"));
        BufferedReader reader = new BufferedReader(new StringReader(input));
        InputController controller = new InputController(reader, wordObj);
        controller.processGuess();
        Assertions.assertThrows(WrongInputException.class, controller::processGuess);
    }
}
