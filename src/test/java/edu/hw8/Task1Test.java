package edu.hw8;

import edu.hw8.task1.AnswerTask;
import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Assertions;

class Task1Test {
    private static final List<String> messages = List.of(
        "интеллект",
        "оскорбления\nличности",
        "глупый\nглупый\nглупый",
        "доброта",
        "");
    private static final List<List<String>> expected = List.of(
        List.of(AnswerTask.ANSWERS.get("интеллект")),
        List.of(AnswerTask.ANSWERS.get("оскорбления"), AnswerTask.ANSWERS.get("личности")),
        List.of(AnswerTask.ANSWERS.get("глупый"), AnswerTask.ANSWERS.get("глупый"), AnswerTask.ANSWERS.get("глупый")),
        List.of(AnswerTask.UNKNOWN_MESSAGE),
        List.of()
    );

    private int getPortNumber() throws IOException {
        try (var socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }

    @Test
    public void clientServerTest() throws InterruptedException, IOException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int portNumber = getPortNumber();
        Server server = new Server(portNumber, 2);
        List<Client> clients = new ArrayList<>();
        for (String message : messages) {
            clients.add(new Client(portNumber, new BufferedReader(new StringReader(message))));
        }
        try {
            server.start();
            List<Future<List<String>>> futures = service.invokeAll(clients);
            for (int i = 0; i < messages.size(); i++) {
                List<String> actual = futures.get(i).get();
                Assertions.assertEquals(expected.get(i), actual);
            }
        } finally {
            service.close();
        }
    }

    @Test
    void serverCreationExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Server(0, 0));
    }
}
