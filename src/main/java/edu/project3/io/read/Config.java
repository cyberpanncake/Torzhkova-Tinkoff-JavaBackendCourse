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
        this.extension = extension == null ? Extension.TXT : extension;
    }

    @SuppressWarnings("InnerAssignment")
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
                String argValue = args[i + 1];
                switch (args[i]) {
                    case "--from" -> startDate = parseStartDate(startDate, argValue);
                    case "--to" -> endDate = parseEndDate(endDate, argValue);
                    case "--format" -> extension = parseExtension(extension, argValue);
                    case null, default -> throw new IllegalConfigException("Неверное имя аргумента");
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

    private static LocalDate parseStartDate(LocalDate startDate, String argValue) throws IllegalConfigException {
        if (startDate != null) {
            throw new IllegalConfigException("Начальная дата уже задана");
        }
        return LocalDate.parse(argValue, DATE_FORMAT);
    }

    private static LocalDate parseEndDate(LocalDate endDate, String argValue) throws IllegalConfigException {
        if (endDate != null) {
            throw new IllegalConfigException("Конечная дата уже задана");
        }
        return LocalDate.parse(argValue, DATE_FORMAT);
    }

    private static Extension parseExtension(Extension extension, String argValue) throws IllegalConfigException {
        if (extension != null) {
            throw new IllegalConfigException("Формат вывода уже задан");
        }
        return Extension.parse(argValue);
    }
}
