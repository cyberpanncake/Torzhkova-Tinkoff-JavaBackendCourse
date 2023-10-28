package edu.hw2.task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        if (command.isEmpty()) {
            throw new IllegalArgumentException("Пустая команда");
        }
        LOGGER.info(command);
    }

    @Override
    public void close() {
        LOGGER.info("Стабильное соединение закрыто");
    }
}
