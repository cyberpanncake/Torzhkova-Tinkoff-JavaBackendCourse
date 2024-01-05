package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Client implements Callable<List<String>> {
    private static final String URL = "localhost";
    private final int portNumber;
    private final BufferedReader clientInputReader;

    public Client(int portNumber, BufferedReader reader) {
        this.portNumber = portNumber;
        this.clientInputReader = reader;
    }

    @Override
    public List<String> call() throws IOException {
        List<String> answers = new ArrayList<>();
        try (Socket socket = new Socket(URL, portNumber)) {
            BufferedReader serverAnswerReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream clientWriter = new PrintStream(socket.getOutputStream());
            while (!Thread.currentThread().isInterrupted()) {
                String message = clientInputReader.readLine();
                if (message == null) {
                    break;
                }
                clientWriter.println(message);
                answers.add(serverAnswerReader.readLine());
            }
        }
        return answers;
    }
}
