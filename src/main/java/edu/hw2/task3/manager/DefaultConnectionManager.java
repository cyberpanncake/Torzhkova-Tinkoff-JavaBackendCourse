package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private static final double DEFAULT_PROBABILITY = 0.5;
    private final double faultyConnectionProbability;

    public DefaultConnectionManager() {
        faultyConnectionProbability = DEFAULT_PROBABILITY;
    }

    public DefaultConnectionManager(double probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Вероятность должна быть от 0 до 1");
        }
        this.faultyConnectionProbability = probability;
    }

    @Override
    public Connection getConnection() {
        if (Math.random() < faultyConnectionProbability) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
