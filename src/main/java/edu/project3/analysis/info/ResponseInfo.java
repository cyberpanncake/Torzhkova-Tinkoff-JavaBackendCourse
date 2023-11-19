package edu.project3.analysis.info;

import edu.project3.io.write.Formatter;
import edu.project3.io.write.table.Table;
import edu.project3.log.HttpResponse;
import java.util.List;

public record ResponseInfo(HttpResponse response, int count) {

    public static Table toTable(List<ResponseInfo> infos) {
        return new Table(new String[]{"Код", "Имя", "Количество"},
            infos.stream()
                .map(i -> new String[]{
                    String.valueOf(i.response.getCode()),
                    i.response.getName(),
                    Formatter.INT.format(i.count)})
                .toList());
    }
}
