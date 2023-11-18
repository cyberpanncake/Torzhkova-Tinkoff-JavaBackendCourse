package edu.project3.analysis.info;

import edu.project3.utils.PrintUtils;
import java.util.List;

public record ResourceInfo(String resource, int count) {
    public static String listToString(List<ResourceInfo> infos) {
        return PrintUtils.printTable(new String[]{"Ресурс", "Количество"},
            infos.stream()
                .map(i -> new String[]{i.resource, String.valueOf(i.count)})
                .toList());
    }
}
