package edu.project3.analysis.info;

import edu.project3.utils.PrintUtils;
import java.util.List;

public record CommonInfo(String name, Object value) {
    public static String listToString(List<CommonInfo> infos) {
        return PrintUtils.printTable(new String[]{"Метрика", "Значение"},
            infos.stream()
                .map(i -> new String[]{i.name, i.value.toString()})
                .toList());
    }
}
