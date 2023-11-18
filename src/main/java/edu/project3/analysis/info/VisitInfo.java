package edu.project3.analysis.info;

import edu.project3.utils.PrintUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record VisitInfo(String ip, long count, LocalDateTime lastVisit) {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");

    public static String listToString(List<VisitInfo> infos) {
        return PrintUtils.printTable(new String[]{"IP-адрес", "Количество", "Последний запрос"},
            infos.stream()
                .map(i -> new String[]{
                    i.ip,
                    String.valueOf(i.count),
                    i.lastVisit.format(DATE_FORMAT)})
                .toList());
    }
}
