package edu.project1.utils;

import edu.project1.game.Word;
import edu.project1.throwable.CreateWordException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RandomWordFromFileUtils {
    private static final String WORDS_FILE_PATH = "src/main/resources/project1/words.txt";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE
        = "Не удалось получить слово для начала игры. Файл \"%s\" не найден.";
    private static final String FILE_IS_EMPTY_ERROR_MESSAGE
        = "Не удалось получить слово для начала игры. Файл \"%s\" пустой.";
    private static final String CANNOT_GET_WORD_ERROR_MESSAGE = "Не удалось получить слово для начала игры.";

    private RandomWordFromFileUtils() {
    }

    public static Word getRandomWord() throws CreateWordException {
        try (BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(WORDS_FILE_PATH), StandardCharsets.UTF_8))) {
            List<String> lines = br.lines().filter(s -> !s.isEmpty()).toList();
            int randomWordIndex = (int) (Math.random() * lines.size());
            return new Word(lines.get(randomWordIndex));
        } catch (FileNotFoundException e) {
            throw new CreateWordException(FILE_NOT_FOUND_ERROR_MESSAGE.formatted(WORDS_FILE_PATH));
        } catch (IOException e) {
            throw new CreateWordException(CANNOT_GET_WORD_ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            throw new CreateWordException(FILE_IS_EMPTY_ERROR_MESSAGE.formatted(WORDS_FILE_PATH));
        }
    }
}
