package edu.project3.analysis.info;

import edu.project3.io.write.Formatter;
import edu.project3.io.write.table.Table;
import java.util.List;

public record ResourceInfo(String resource, int count) {

    public static Table toTable(List<ResourceInfo> infos) {
        return new Table(new String[]{"Ресурс", "Количество"},
            infos.stream()
                .map(i -> new String[]{i.resource,
                    Formatter.INT.format(i.count)})
                .toList());
    }
}
