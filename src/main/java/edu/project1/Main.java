package edu.project1;

import edu.project1.game.Game;
import edu.project1.throwable.CreateWordException;
import edu.project1.throwable.NeedToStopGameEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
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
        String encoding = getEncoding();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, encoding))) {
            LOGGER.info(START_MESSAGE.formatted(Game.TOTAL_ATTEMPTS));
            while (true) {
                try {
                    Game game = new Game(reader);
                    boolean isWordGuessed = game.play();
                    if (isWordGuessed) {
                        LOGGER.info("Вы выиграли!");
                    } else {
                        LOGGER.info("Вы проиграли, попытки закончились");
                    }
                    LOGGER.info(CONTINUE_QUESTION_MESSAGE);
                    String input = reader.readLine();
                    if (!"да".equalsIgnoreCase(input)) {
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
                    if (!"да".equalsIgnoreCase(input)) {
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
        LOGGER.info("Игра завершена");
    }

    @SuppressWarnings("MagicNumber")
    private static String getEncoding() {
        LOGGER.info("Choose encoding (input 1, 2 or 3):\n1 - windows-1251\n2 - UTF-8\n3 - ASCII");
        Scanner in = new Scanner(System.in);
            boolean encodingIsSet = false;
            int encodingNumber = 0;
            while (!encodingIsSet) {
                try {
                    encodingNumber = Integer.parseInt(in.nextLine());
                    if (encodingNumber < 1 || encodingNumber > 3) {
                        throw new Exception();
                    }
                    encodingIsSet = true;
                } catch (Exception e) {
                    LOGGER.warn("Wrong input, try again");
                }
            }
            return switch (encodingNumber) {
                case 1 -> "windows-1251";
                case 2 -> "UTF-8";
                case 3 -> "ASCII";
                default -> throw new IllegalStateException("Unexpected value: " + encodingNumber);
            };
    }
}
