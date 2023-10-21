package edu.project1;

import edu.project1.game.Game;
import edu.project1.throwable.CreateWordException;
import edu.project1.throwable.NeedToStopGameEvent;
import edu.project1.throwable.WrongInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    private static final String START_MESSAGE = "Добро пожаловать в игру \"Виселица\". "
        + "На отгадывание одного слова даётся %d попыток. Для прекращения игры нажмите Ctrl + D.";
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "windows-1251"))) {
        LOGGER.info(START_MESSAGE.formatted(Game.TOTAL_ATTEMPTS));
            while (true) {
                try {
                    Game game = new Game(reader);
                    game.play();
                    LOGGER.info("Начать игру ещё раз? (да/нет)");
                    String input = reader.readLine();
                    if (input == null || !"да".equals(input.toLowerCase())) {
                        return;
                    }
                } catch (CreateWordException e) {
                    LOGGER.error(e.getMessage());
                    LOGGER.info("Начать игру ещё раз? (да/нет)");
                    String input = null;
                    try {
                        input = reader.readLine();
                    } catch (IOException ex) {
                        LOGGER.error("Ошибка чтения ввода");
                    }
                    if (input == null || !"да".equals(input.toLowerCase())) {
                        return;
                    }
                } catch (NeedToStopGameEvent e) {
                    return;
                } catch (IOException e) {
                    LOGGER.error("Ошибка чтения ввода");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
