package edu.hw2.task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double DEFAULT_PROBABILITY = 0.5;
    private final double failProbability;

    public FaultyConnection() {
        failProbability = DEFAULT_PROBABILITY;
    }

    public FaultyConnection(double probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Вероятность должна быть от 0 до 1");
        }
        this.failProbability = probability;
    }

    @Override
    public void execute(String command) {
        if (command.isEmpty()) {
            throw new IllegalArgumentException("Пустая команда");
        }
        if (Math.random() < failProbability) {
            throw new ConnectionException("Соединение не работает");
        } else {
            LOGGER.info(command);
        }
    }

    @Override
    public void close() {
        LOGGER.info("Проблемное соединение закрыто");
    }
}
