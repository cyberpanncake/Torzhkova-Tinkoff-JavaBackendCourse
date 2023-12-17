package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    private final int maxConnectionsCount;
    private final int portNumber;

    public Server(int portNumber, int maxConnectionsCount) {
        if (maxConnectionsCount < 1) {
            throw new IllegalArgumentException("Максимальное количество соединений не может быть меньше 1");
        }
        this.portNumber = portNumber;
        this.maxConnectionsCount = maxConnectionsCount;
    }

    @Override
    public void run() {
        try (ServerSocket socket = new ServerSocket(portNumber);
             ExecutorService service = Executors.newFixedThreadPool(maxConnectionsCount)) {
            socket.setReuseAddress(true);
            while (!isInterrupted()) {
                Socket accepted = socket.accept();
                service.execute(new AnswerTask(accepted));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при работе сервера");
        }
    }
}
