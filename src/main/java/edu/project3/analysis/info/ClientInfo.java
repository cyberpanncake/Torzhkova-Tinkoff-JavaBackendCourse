package edu.project3.analysis.info;

import edu.project3.io.write.Formatter;
import edu.project3.io.write.table.Table;
import java.time.LocalDateTime;
import java.util.List;

public record ClientInfo(String ip, long count, LocalDateTime lastVisit) {


    public static Table toTable(List<ClientInfo> infos) {
        return new Table(new String[]{"IP-адрес", "Количество", "Последний запрос"},
            infos.stream()
                .map(i -> new String[]{
                    i.ip,
                    Formatter.INT.format(i.count),
                    i.lastVisit.format(Formatter.DATETIME)})
                .toList());
    }
}
