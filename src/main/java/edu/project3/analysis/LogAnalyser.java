package edu.project3.analysis;

import edu.project3.Config;
import edu.project3.analysis.info.CommonInfo;
import edu.project3.analysis.info.ResourceInfo;
import edu.project3.analysis.info.ResponseInfo;
import edu.project3.analysis.info.VisitInfo;
import edu.project3.log.Log;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogAnalyser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");
    private static final DecimalFormat INT_FORMAT = new DecimalFormat("###,###");
    private final List<Log> logs;
    private final Config config;

    public LogAnalyser(List<Log> logs, Config config) {
        this.logs = logs.stream().toList();
        this.config = config;
    }

    public List<CommonInfo> getCommonInfo() {
        List<CommonInfo> infos = new ArrayList<>();
        infos.add(new CommonInfo("Файл(-ы)", String.join("\n", config.paths())));
        infos.add(new CommonInfo("Начальная дата", config.startDate().format(DATE_FORMAT)));
        infos.add(new CommonInfo("Конечная дата", config.endDate().format(DATE_FORMAT)));
        infos.add(new CommonInfo("Количество запросов", INT_FORMAT.format(logs.size())
            .replace(" ", "_")));
        infos.add(new CommonInfo("Средний размер ответа", toByteFormat((long) logs.stream()
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
        return logs.stream()
            .collect(Collectors.groupingBy(Log::resource, Collectors.summingInt(a -> 1)))
            .entrySet().stream()
            .map(e -> new ResourceInfo(e.getKey(), e.getValue()))
            .sorted((r1, r2) -> r2.count() - r1.count())
            .limit(k)
            .toList();
    }

    public List<ResponseInfo> getMostPopularResponses(int k) {
        return logs.stream()
            .collect(Collectors.groupingBy(Log::response, Collectors.summingInt(a -> 1)))
            .entrySet().stream()
            .map(e -> new ResponseInfo(e.getKey(), e.getValue()))
            .sorted((r1, r2) -> r2.count() - r1.count())
            .limit(k)
            .toList();
    }

    /**
     * Получает информацию о посещениях: ip-адрес клиента, количество запросов и дату последнего посещения
     */
    public List<VisitInfo> getVisitsInfo() {
        return logs.stream()
            .collect(Collectors.groupingBy(Log::ip, Collectors.toList()))
            .entrySet().stream()
            .map(e -> {
                List<Log> visits = e.getValue();
                long count = visits.size();
                LocalDateTime lastVisit = visits.stream()
                    .map(Log::date)
                    .max(Comparator.naturalOrder())
                    .orElse(LocalDateTime.MIN);
                return new VisitInfo(e.getKey(), count, lastVisit);
            })
            .toList();
    }
}
