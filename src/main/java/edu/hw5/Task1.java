package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task1 {
    private Task1() {
    }

    public static String getAvgDuration(String[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Массив строк не должен быть null");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        Duration duration = Duration.ZERO;
        for (String s : input) {
            if (s == null) {
                throw new IllegalArgumentException("Строка не должен быть null");
            }
            String[] range = s.split(" - ");
            LocalDateTime start = LocalDateTime.parse(range[0], formatter);
            LocalDateTime end = LocalDateTime.parse(range[1], formatter);
            if (end.isBefore(start)) {
                throw new IllegalArgumentException("Конец диапазона не может быть меньше начала");
            }
            duration = duration.plus(Duration.between(start, end));
        }
        if (input.length != 0) {
            duration = duration.dividedBy(input.length);
        }
        return "%dч %dм".formatted(duration.toHoursPart(), duration.toMinutesPart());
    }
}
