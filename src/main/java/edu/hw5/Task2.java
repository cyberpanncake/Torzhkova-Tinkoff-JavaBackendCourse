package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static final int MONTHS_COUNT = 12;
    private static final int DAY = 13;

    private Task2() {
    }

    public static List<LocalDate> getFridays13InYear(int year) {
        if (year < LocalDate.MIN.getYear() || year > LocalDate.MAX.getYear()) {
            throw new IllegalArgumentException("Неверный год");
        }
        List<LocalDate> fridays = new ArrayList<>();
        for (int month = 1; month <= MONTHS_COUNT; month++) {
            LocalDate day = LocalDate.of(year, month, DAY);
            if (day.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(day);
            }
        }
        return fridays;
    }

    public static LocalDate getNextFriday13(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Дата не должна быть null");
        }
        TemporalAdjuster adjuster = t -> {
            LocalDate d = (LocalDate) t;
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            LocalDate start13 = (day > DAY ? d.plusMonths(1) : LocalDate.of(year, month, DAY))
                .withDayOfMonth(DAY);
            while (true) {
                if (start13.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    return start13;
                }
                start13 = start13.plusMonths(1).withDayOfMonth(DAY);
            }
        };
        return (LocalDate) adjuster.adjustInto(date);
    }
}
