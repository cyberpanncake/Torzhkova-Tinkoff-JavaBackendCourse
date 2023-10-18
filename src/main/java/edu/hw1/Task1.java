package edu.hw1;

public class Task1 {
    private static final String TIME_PATTERN = "[0-9]{2,}:[0-5][0-9]";
    private static final int SECONDS_IN_MINUTE = 60;

    public int minutesToSeconds(String timeInput) {
        if (timeInput == null) {
            throw new IllegalArgumentException("Входная строка не может быть null");
        }
        if (!timeInput.matches(TIME_PATTERN)) {
            return -1;
        }
        String[] timeParts = timeInput.split(":");
        int totalSeconds = 0;
        totalSeconds += Integer.parseInt(timeParts[0]) * SECONDS_IN_MINUTE;
        totalSeconds += Integer.parseInt(timeParts[1]);
        return totalSeconds;
    }
}
