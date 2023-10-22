package edu.hw2.task3.connection;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
