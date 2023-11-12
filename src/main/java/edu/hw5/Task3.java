package edu.hw5;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class Task3 {
    private Task3() {
    }

    private static Map<String, String> patterns = Map.of(
        "\\d{4}-\\d{2}-\\d{2}", "yyyy-MM-dd",
        "\\d{4}-\\d{1,2}-\\d{1,2}", "yyyy-M-d",
        "\\d{1,2}/\\d{1,2}/\\d{4}", "d/M/yyyy",
        "\\d{1,2}/\\d{1,2}/\\d{2}", "d/M/yy"
    );

    public static Optional<LocalDate> parseDate(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Строка не должна быть null");
        }
        LocalDate result = null;
        try {
            for (String patten : patterns.keySet()) {
                if (string.matches(patten)) {
                    result = LocalDate.parse(string, DateTimeFormatter.ofPattern(patterns.get(patten)));
                    break;
                }
            }
            if (string.matches("tomorrow")) {
                result = LocalDate.now().plusDays(1);
            } else if (string.matches("today")) {
                result = LocalDate.now();
            } else if (string.matches("yesterday") || string.matches("1 day ago")) {
                result = LocalDate.now().minusDays(1);
            } else if (string.matches("([2-9]|\\d{2,}) days ago")) {
                int days = Integer.parseInt(string.split(" ")[0]);
                result = LocalDate.now().minusDays(days);
            }
        } catch (DateTimeParseException e) {
        }
        return Optional.ofNullable(result);
    }
}
