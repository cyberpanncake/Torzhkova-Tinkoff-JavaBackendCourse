package edu.project3.io.write;

import edu.project3.analysis.LogAnalyser;
import edu.project3.analysis.info.ClientInfo;
import edu.project3.analysis.info.CommonInfo;
import edu.project3.analysis.info.HourInfo;
import edu.project3.analysis.info.ResourceInfo;
import edu.project3.analysis.info.ResponseInfo;
import edu.project3.io.read.Config;
import edu.project3.io.write.table.Table;
import edu.project3.io.write.table.TablePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

class LogInfoWriterTest {
    private static final String LOGS_PATH = "src/main/resources/project3/logs.txt";
    private static final String LOGS_STAT_PATH = "src/main/resources/project3/logs_stat.%s";
    private static final String DEF_LOGS_STAT_PATH = "src/main/resources/project3/logs_stat_%s.%s";
    private static final int TOP_SIZE = 3;
    private final Map<String, Table> infos;

    LogInfoWriterTest() throws IOException {
        Config config = new Config(LOGS_PATH, (LocalDate) null, null, null);
        LogAnalyser analyser = new LogAnalyser(config);
        this.infos = new TreeMap<>(Comparator.naturalOrder()) {
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
    }

    @ParameterizedTest
    @MethodSource("writers")
    @SuppressWarnings("MagicNumber")
    public void writeTest(LogInfoWriter writer, Extension extension) throws IOException {
        String absolutePath = writer.write(infos, LOGS_STAT_PATH.formatted(extension.getExt()));
        Path path = Path.of(absolutePath);
        Assertions.assertTrue(Files.exists(path));
        Files.delete(path);
    }

    @ParameterizedTest
    @MethodSource("writers")
    @SuppressWarnings("MagicNumber")
    public void writeDefaultTest(LogInfoWriter writer, Extension extension) throws IOException {
        String actual = writer.write(infos);
        Path path = Path.of(actual);
        String expected = Path.of(DEF_LOGS_STAT_PATH.formatted(
            LocalDateTime.now().format(Formatter.FILE_DATE), extension.getExt()))
            .toAbsolutePath().toString();
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(Files.exists(path));
        Files.delete(path);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> writers() {
        return Stream.of(
            Arguments.of(new TxtLogInfoWriter(), Extension.TXT),
            Arguments.of(new MarkdownLogInfoWriter(), Extension.MARKDOWN),
            Arguments.of(new AdocLogInfoWriter(), Extension.ADOC)
        );
    }
}
