package edu.project3;

import edu.project3.analysis.LogAnalyser;
import edu.project3.analysis.info.ClientInfo;
import edu.project3.analysis.info.CommonInfo;
import edu.project3.analysis.info.HourInfo;
import edu.project3.analysis.info.ResourceInfo;
import edu.project3.analysis.info.ResponseInfo;
import edu.project3.io.read.Config;
import edu.project3.io.write.AdocLogInfoWriter;
import edu.project3.io.write.LogInfoWriter;
import edu.project3.io.write.MarkdownLogInfoWriter;
import edu.project3.io.write.TxtLogInfoWriter;
import edu.project3.io.write.table.Table;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TOP_SIZE = 3;

    private Main() {
    }

    public static void main(String[] args) {
        try {
            Config config = Config.parseArgs(args);
            LogAnalyser analyser = new LogAnalyser(config);
            Map<String, Table> infos = new TreeMap<>(Comparator.naturalOrder()) {
            };
            infos.put(
                "1. Общая информация",
                CommonInfo.toTable(analyser.getCommonInfo())
            );
            infos.put(
                "2. Запрашиваемые ресурсы",
                ResourceInfo.toTable(analyser.getMostPopularResources(TOP_SIZE))
            );
            infos.put(
                "3. Коды ответа",
                ResponseInfo.toTable(analyser.getMostPopularResponses(TOP_SIZE))
            );
            infos.put(
                "4. Статистика клиентов",
                ClientInfo.toTable(analyser.getMostActiveClients(TOP_SIZE))
            );
            infos.put(
                "5. Почасовая нагрузка на сервер",
                HourInfo.toTable(analyser.getHoursInfo())
            );
            LogInfoWriter writer = switch (config.extension()) {
                case MARKDOWN -> new MarkdownLogInfoWriter();
                case ADOC -> new AdocLogInfoWriter();
                case TXT -> new TxtLogInfoWriter();
            };
            String absolutePath = writer.write(infos);
            LOGGER.info("Статистика по логам успешно записана в файл \"%s\"".formatted(absolutePath));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
