package edu.project1.game;

import edu.project1.throwable.NeedToStopGameEvent;
import edu.project1.throwable.WrongInputException;
import java.io.BufferedReader;
import java.io.IOException;

public class InputController {
    private final BufferedReader reader;
    private String inputtedLetters = "";
    private final Word word;

    public InputController(BufferedReader reader, Word word) {
        this.reader = reader;
        this.word = word;
    }

    public boolean processGuess() throws NeedToStopGameEvent, WrongInputException, IOException {
        try {
            String input = reader.readLine();
            if (input == null) {
                throw new NeedToStopGameEvent();
            }
            if (input.isEmpty()) {
                throw new WrongInputException("Неверный ввод. Введите букву");
            }
            if (input.length() > 1) {
                throw new WrongInputException("Неверный ввод. Введите только 1 букву");
            }
            input = input.toLowerCase();
            if (!input.matches("[а-я]")) {
                throw new WrongInputException("Неверный ввод. Введите букву русского алфавита");
            }
            if (inputtedLetters.contains(input)) {
                throw new WrongInputException("Неверный ввод. Вы уже вводили эту букву");
            }
            inputtedLetters += input;
            return word.guessLetter(input.charAt(0));
        } catch (IOException e) {
            throw new IOException("Ошибка чтения ввода");
        }
    }
}
