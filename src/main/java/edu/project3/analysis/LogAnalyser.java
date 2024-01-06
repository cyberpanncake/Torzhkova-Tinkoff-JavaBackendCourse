package edu.project3.analysis;

import edu.project3.analysis.info.ClientInfo;
import edu.project3.analysis.info.CommonInfo;
import edu.project3.analysis.info.HourInfo;
import edu.project3.analysis.info.ResourceInfo;
import edu.project3.analysis.info.ResponseInfo;
import edu.project3.io.read.Config;
import edu.project3.io.read.LogReader;
import edu.project3.io.read.LogsData;
import edu.project3.io.write.Formatter;
import edu.project3.log.Log;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.time.temporal.ChronoUnit.DAYS;

public class LogAnalyser {
    private final LogsData logsData;
    private final Config config;

    public LogAnalyser(Config config) throws IOException {
        this.logsData = LogReader.read(config);
        this.config = config;
    }

    public List<CommonInfo> getCommonInfo() {
        List<CommonInfo> infos = new ArrayList<>();
        infos.add(new CommonInfo("Файл(-ы)", String.join("\n", logsData.paths())));
        infos.add(new CommonInfo("Начальная дата", config.startDate().equals(LocalDateTime.MIN) ? "-"
            : config.startDate().format(Formatter.DATETIME)));
        infos.add(new CommonInfo("Конечная дата", config.endDate().equals(LocalDateTime.MAX) ? "-"
            : config.endDate().format(Formatter.DATETIME)));
        infos.add(new CommonInfo("Количество запросов", Formatter.INT.format(logsData.logs().size())));
        infos.add(new CommonInfo("Средний размер ответа", toByteFormat((long) logsData.logs().stream()
            .mapToLong(Log::fileSize)
            .average()
            .orElse(0))));
        return infos;
    }

    @SuppressWarnings("MagicNumber")
    private String toByteFormat(long bytes) {
        long gb = bytes / 1024 / 1024 / 1024;
        long mb = bytes / 1024 / 1024 % 1024;
        long kb = bytes / 1024 % 1024 % 1024;
        long b = bytes % 1024 % 1024 % 1024;
        return (gb > 0 ? gb + "Gb " : "") + (mb > 0 ? mb + "Mb " : "") + (kb > 0 ? kb + "Kb " : "") + b + "b";
    }

    public List<ResourceInfo> getMostPopularResources(int k) {
        return logsData.logs().stream()
            .collect(Collectors.groupingBy(Log::resource, Collectors.summingInt(a -> 1)))
            .entrySet().stream()
            .map(e -> new ResourceInfo(e.getKey(), e.getValue()))
            .sorted((r1, r2) -> r2.count() - r1.count())
            .limit(k)
            .toList();
    }

    public List<ResponseInfo> getMostPopularResponses(int k) {
        return logsData.logs().stream()
            .collect(Collectors.groupingBy(Log::response, Collectors.summingInt(a -> 1)))
            .entrySet().stream()
            .map(e -> new ResponseInfo(e.getKey(), e.getValue()))
            .sorted((r1, r2) -> r2.count() - r1.count())
            .limit(k)
            .toList();
    }

    /**
     * Получает статистику по самым активным клиентам:
     * ip-адрес клиента, количество запросов и дату последнего посещения
     */
    public List<ClientInfo> getMostActiveClients(int k) {
        return logsData.logs().stream()
            .collect(Collectors.groupingBy(Log::ip, Collectors.toList()))
            .entrySet().stream()
            .map(e -> {
                List<Log> visits = e.getValue();
                long count = visits.size();
                LocalDateTime lastVisit = visits.stream()
                    .map(Log::date)
                    .max(Comparator.naturalOrder())
                    .orElse(LocalDateTime.MIN);
                return new ClientInfo(e.getKey(), count, lastVisit);
            })
            .sorted((i1, i2) -> Math.toIntExact(i2.count() - i1.count()))
            .limit(k)
            .toList();
    }

    /**
     * Получает статистику загруженности сервера:
     * час суток, среднее количество запросов в этот час, процентное значения количества запросов
     */
    @SuppressWarnings("MagicNumber")
    public List<HourInfo> getHoursInfo() {
        LocalDate start = logsData.logs().stream()
            .map(Log::date)
            .min(Comparator.naturalOrder())
            .orElse(LocalDateTime.MIN)
            .toLocalDate();
        LocalDate end = logsData.logs().stream()
            .map(Log::date)
            .max(Comparator.naturalOrder())
            .orElse(LocalDateTime.MIN)
            .toLocalDate().plusDays(1);
        long days = DAYS.between(start, end);
        Map<Integer, HourInfo> hours = logsData.logs().stream()
            .collect(Collectors.groupingBy(l -> l.date().getHour(), Collectors.summingInt(a -> 1)))
            .entrySet().stream()
            .map(e -> new HourInfo(e.getKey(), e.getValue() / (float) days,
                e.getValue() / (float) logsData.logs().size()))
            .collect(Collectors.toMap(HourInfo::hour, h -> h));
        for (int h = 0; h < 24; h++) {
            if (!hours.containsKey(h)) {
                hours.put(h, new HourInfo(h, 0, 0));
            }
        }
        return hours.values().stream()
            .sorted(Comparator.comparing(HourInfo::hour))
            .toList();
    }
}
