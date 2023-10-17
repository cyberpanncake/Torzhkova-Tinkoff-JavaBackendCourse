package edu.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomWordFromFileUtils {
    private static final String WORDS_FILE_PATH = "src/main/resources/project1/words.txt";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "Не удалось получить слово для начала игры. Файл \"%s\" не найден.";
    private static final String CANNOT_GET_WORD_ERROR_MESSAGE = "Не удалось получить слово для начала игры.";

    private RandomWordFromFileUtils() {
    }

    public static String getRandomWord() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(WORDS_FILE_PATH))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            int randomWordIndex = new Random().nextInt(lines.size());
            return lines.get(randomWordIndex);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(FILE_NOT_FOUND_ERROR_MESSAGE.formatted(WORDS_FILE_PATH));
        } catch (IOException e) {
            throw new IOException(CANNOT_GET_WORD_ERROR_MESSAGE);
        }
    }
}
