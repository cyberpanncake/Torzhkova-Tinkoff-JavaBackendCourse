package edu.project3.analysis.info;

import edu.project3.io.write.Formatter;
import edu.project3.io.write.table.Table;
import java.util.List;

public record HourInfo(int hour, float average, float percent) {

    @SuppressWarnings("MagicNumber")
    public static Table toTable(List<HourInfo> infos) {
        return new Table(new String[]{"Час", "Количество", "Процент"},
            infos.stream()
                .map(i -> new String[]{
                    "%02d".formatted(i.hour),
                    Formatter.FLOAT.format(i.average),
                    Formatter.FLOAT.format(i.percent * 100) + "%"})
                .toList());
    }
}
