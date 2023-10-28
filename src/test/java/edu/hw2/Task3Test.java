package edu.hw2;

import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.ConnectionException;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import edu.hw2.task3.manager.ConnectionManager;
import edu.hw2.task3.manager.DefaultConnectionManager;
import edu.hw2.task3.manager.FaultyConnectionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void popularCommandExecutorSuccessTest() {
        ConnectionManager manager = new DefaultConnectionManager(0);
        int maxAttempts = 1;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        int expected = 0;
        int actual = 0;
        for (int i = 0; i < 10; i++) {
            try {
                executor.updatePackages();
            } catch (Exception e) {
                actual++;
            }
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void popularCommandExecutorFailTest() {
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 1;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);
        int actual = 0;
        for (int i = 0; i < 100; i++) {
            try {
                executor.updatePackages();
            } catch (Exception e) {
                actual++;
            }
        }
        Assertions.assertTrue(actual > 0);
    }

    @Test
    public void stableConnectionCreationTest() {
        ConnectionManager manager = new DefaultConnectionManager(0);
        Connection connection = manager.getConnection();
        Assertions.assertTrue(connection instanceof StableConnection);
    }

    @Test
    public void faultyConnectionCreationTest() {
        ConnectionManager manager = new FaultyConnectionManager();
        Connection connection = manager.getConnection();
        Assertions.assertTrue(connection instanceof FaultyConnection);
    }

    @Test
    public void faultyConnectionFailedTest() {
        Connection connection = new FaultyConnection(1);
        Assertions.assertThrows(ConnectionException.class, () -> connection.execute("command"));
    }

    @RepeatedTest(50)
    public void emptyCommandExceptionTest() {
        ConnectionManager manager = new DefaultConnectionManager();
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.getConnection().execute(""));
    }
}
