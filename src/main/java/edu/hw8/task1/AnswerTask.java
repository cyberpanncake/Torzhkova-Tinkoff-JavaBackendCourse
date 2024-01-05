package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;

public class AnswerTask implements Runnable {
    public static final Map<String, String> ANSWERS = Map.of(
        "личности", "Не переходи на личности там, где их нет.",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления."
    );
    public static final String UNKNOWN_MESSAGE = "На эту фразу нет ответа";
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintStream writer;

    public AnswerTask(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String message = reader.readLine();
                if (message == null) {
                    break;
                }
                writer.println(ANSWERS.getOrDefault(message, UNKNOWN_MESSAGE));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении сообщения с клиента");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при закрытии сокета");
            }
        }
    }
}
