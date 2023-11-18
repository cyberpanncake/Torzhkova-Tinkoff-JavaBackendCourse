package edu.project3.analysis.info;

import edu.project3.utils.PrintUtils;
import java.util.List;

public record ResponseInfo(HttpResponse response, int count) {
    public static String listToString(List<ResponseInfo> infos) {
        return PrintUtils.printTable(new String[]{"Код", "Имя", "Количество"},
            infos.stream()
                .map(i -> new String[]{
                    String.valueOf(i.response.getCode()),
                    i.response.getName(),
                    String.valueOf(i.count)})
                .toList());
    }
}
