package edu.project3.io.read;

import edu.project3.io.write.Extension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public record Config(String path, LocalDateTime startDate, LocalDateTime endDate, Extension extension) {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Config(String path, LocalDateTime startDate, LocalDateTime endDate, Extension extension) {
        this.path = path;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extension = extension == null ? Extension.MARKDOWN : extension;
    }

    public static Config parseArgs(String[] args) throws IllegalConfigException {
        String path;
        if ("--path".equals(args[0])) {
            path = args[1];
        } else {
            throw new IllegalConfigException("Не задан путь к логам");
        }
        LocalDate startDate = null;
        LocalDate endDate = null;
        Extension extension = null;
        try {
            for (int i = 2; i < args.length; i += 2) {
                String argName = args[i];
                String argValue = args[i + 1];
                if ("--from".equals(argName)) {
                    if (startDate != null) {
                        throw new IllegalConfigException("Начальная дата уже задана");
                    }
                    startDate = LocalDate.parse(argValue, DATE_FORMAT);
                } else if ("--to".equals(argName)) {
                    if (endDate != null) {
                        throw new IllegalConfigException("Конечная дата уже задана");
                    }
                    endDate = LocalDate.parse(argValue, DATE_FORMAT);
                } else if ("--format".equals(argName)) {
                    if (extension != null) {
                        throw new IllegalConfigException("Формат вывода уже задан");
                    }
                    extension = Extension.parse(argValue);
                } else {
                    throw new IllegalConfigException("Неверное имя аргумента");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalConfigException("Не задано значение аргумента");
        } catch (DateTimeParseException e) {
            throw new IllegalConfigException("Неверный формат даты");
        }
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalConfigException("Начальная дата больше конечной");
        }
        return new Config(
            path,
            startDate == null ? LocalDateTime.MIN : startDate.atStartOfDay(),
            endDate == null ? LocalDateTime.MAX : endDate.plusDays(1).atStartOfDay().minusNanos(1),
            extension);
    }
}
