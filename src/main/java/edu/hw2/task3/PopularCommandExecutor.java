package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.ConnectionException;
import edu.hw2.task3.manager.ConnectionManager;

public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        if (manager == null) {
            throw new IllegalArgumentException("Менеджер соединения не может быть null");
        }
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Количество попыток установки соединения должно быть больше 0");
        }
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        Exception exc = null;
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (Exception e) {
                exc = e;
            }
        }
        if (exc != null) {
            throw new ConnectionException("Не удалось выполнить команду", exc);
        }
    }
}
