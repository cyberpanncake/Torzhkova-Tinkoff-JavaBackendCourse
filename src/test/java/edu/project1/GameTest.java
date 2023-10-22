package edu.project1;

import edu.project1.game.Game;
import edu.project1.throwable.CreateWordException;
import edu.project1.throwable.NeedToStopGameEvent;
import edu.project1.throwable.WrongInputException;
import edu.project1.utils.WordGuessTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class GameTest {
    private final WordGuessTestUtils wordGuessTestUtils = new WordGuessTestUtils();

    private static Stream<Arguments> parametersWords() {
        return WordGuessTestUtils.parametersWords();
    }

    private char getNextLetterNotInWord(String word) {
        return wordGuessTestUtils.getNextLetterNotInWord(word);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void playWinTest(String word)
        throws CreateWordException, NeedToStopGameEvent, IOException {
        Set<String> lettersSet = new HashSet<>(List.of(word.split("")));
        String letters = String.join("\n", lettersSet);
        boolean expected = true;
        BufferedReader reader = new BufferedReader(new StringReader(letters));
        Game game = new Game(reader, word);
        boolean actual = game.play();
        Assertions.assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("parametersWords")
    public void playLossTest(String word)
        throws CreateWordException, NeedToStopGameEvent, IOException {
        List<String> lettersList = new ArrayList<>();
        for (int i = 0; i < Game.TOTAL_ATTEMPTS; i++) {
            lettersList.add(String.valueOf(getNextLetterNotInWord(word)));
        }
        String letters = String.join("\n", lettersList);
        boolean expected = false;
        BufferedReader reader = new BufferedReader(new StringReader(letters));
        Game game = new Game(reader, word);
        boolean actual = game.play();
        Assertions.assertEquals(expected,actual);
    }
}
