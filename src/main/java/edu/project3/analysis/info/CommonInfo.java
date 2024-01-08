package edu.project3.analysis.info;

import edu.project3.io.write.table.Table;
import java.util.List;

public record CommonInfo(String name, Object value) {
    public static Table toTable(List<CommonInfo> infos) {
        return new Table(new String[]{"Метрика", "Значение"},
            infos.stream()
                .map(i -> new String[]{i.name, i.value.toString()})
                .toList());
    }
}
