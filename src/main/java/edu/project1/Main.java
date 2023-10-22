package edu.project1;

import edu.project1.game.Game;
import edu.project1.throwable.CreateWordException;
import edu.project1.throwable.NeedToStopGameEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final String START_MESSAGE = "Добро пожаловать в игру \"Виселица\". "
        + "На отгадывание одного слова даётся %d попыток. Для прекращения игры нажмите Ctrl + D.";
    private static final String INPUT_ERROR_MESSAGE = "Ошибка чтения ввода";
    private static final String CONTINUE_QUESTION_MESSAGE = "Начать игру ещё раз? (да/нет)";
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "windows-1251"))) {
        LOGGER.info(START_MESSAGE.formatted(Game.TOTAL_ATTEMPTS));
            while (true) {
                try {
                    Game game = new Game(reader);
                    game.play();
                    LOGGER.info(CONTINUE_QUESTION_MESSAGE);
                    String input = reader.readLine();
                    if (input == null || !"да".equals(input.toLowerCase())) {
                        break;
                    }
                } catch (CreateWordException e) {
                    LOGGER.error(e.getMessage());
                    LOGGER.info(CONTINUE_QUESTION_MESSAGE);
                    String input = null;
                    try {
                        input = reader.readLine();
                    } catch (IOException ex) {
                        LOGGER.error(INPUT_ERROR_MESSAGE);
                    }
                    if (input == null || !"да".equals(input.toLowerCase())) {
                        break;
                    }
                } catch (NeedToStopGameEvent e) {
                    break;
                } catch (IOException e) {
                    LOGGER.error(INPUT_ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
