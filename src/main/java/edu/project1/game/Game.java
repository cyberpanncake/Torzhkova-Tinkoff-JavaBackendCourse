package edu.project1.game;

import edu.project1.throwable.CreateWordException;
import edu.project1.throwable.NeedToStopGameEvent;
import edu.project1.throwable.WrongInputException;
import edu.project1.utils.RandomWordFromFileUtils;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final String START_MESSAGE = "Загаданное слово имеет %d букв(ы). Осталось %d попыток";
    private static final String SUCCESS_MESSAGE = "Угадали!";
    private static final String FAIL_MESSAGE = "Такой буквы нет. Осталось попыток: %d";
    public static final int TOTAL_ATTEMPTS = 5;
    private final Word word;
    private final InputController inputController;
    private int attemptsLeft = TOTAL_ATTEMPTS;

    public Game(BufferedReader reader) throws CreateWordException {
        this.word = RandomWordFromFileUtils.getRandomWord();
        this.inputController = new InputController(reader, word);
    }

    public Game(BufferedReader reader, String wordStr) throws CreateWordException {
        this.word = new Word(wordStr);
        this.inputController = new InputController(reader, word);
    }

    public boolean play() throws NeedToStopGameEvent, IOException {
        LOGGER.info(START_MESSAGE.formatted(word.getLength(), attemptsLeft));
        boolean totalWordGuessed = false;
        while (attemptsLeft > 0 && !totalWordGuessed) {
            Boolean guessResult = null;
            while (guessResult == null) {
                try {
                    LOGGER.info("Введите букву:");
                    guessResult = inputController.processGuess();
                } catch (WrongInputException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
            if (guessResult) {
                LOGGER.info(SUCCESS_MESSAGE);
                totalWordGuessed = word.isAllLettersGuessed();
            } else {
                LOGGER.info(FAIL_MESSAGE.formatted(--attemptsLeft));
            }
            LOGGER.info("Слово: %s".formatted(word.getView()));
        }
        return totalWordGuessed;
    }
}
