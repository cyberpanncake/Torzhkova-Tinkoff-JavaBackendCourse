package edu.project3;

import edu.project3.analysis.LogAnalyser;
import edu.project3.analysis.info.ClientInfo;
import edu.project3.analysis.info.CommonInfo;
import edu.project3.analysis.info.HourInfo;
import edu.project3.analysis.info.ResourceInfo;
import edu.project3.analysis.info.ResponseInfo;
import edu.project3.io.read.Config;
import edu.project3.io.write.table.AdocTablePrinter;
import edu.project3.io.write.table.MarkdownTablePrinter;
import edu.project3.io.write.table.Table;
import edu.project3.io.write.table.TablePrinter;
import edu.project3.io.write.table.TxtTablePrinter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TablePrinterTest {
    private static final String LOGS_PATH = "src/main/resources/project3/logs.txt";
    private static final int TOP_SIZE = 3;
    private final List<Table> tables;

    TablePrinterTest() throws IOException {
        Config config = new Config(LOGS_PATH, (LocalDate) null, null, null);
        LogAnalyser analyser = new LogAnalyser(config);
        this.tables = List.of(
            CommonInfo.toTable(analyser.getCommonInfo()),
            ResourceInfo.toTable(analyser.getMostPopularResources(TOP_SIZE)),
            ResponseInfo.toTable(analyser.getMostPopularResponses(TOP_SIZE)),
            ClientInfo.toTable(analyser.getMostActiveClients(TOP_SIZE)),
            HourInfo.toTable(analyser.getHoursInfo())
        );
    }

    @ParameterizedTest
    @MethodSource("printersCommonInfo")
    @SuppressWarnings("MagicNumber")
    public void printTableCommonInfoTest(TablePrinter printer, String expected) {
        String actual = printer.printTable(tables.get(0));
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> printersCommonInfo() {
        return Stream.of(
            Arguments.of(
                new TxtTablePrinter(),
                """
                    ┌───────────────────────┬──────────┐
                    │ Метрика               │ Значение │
                    ╞═══════════════════════╪══════════╡
                    │ Файл(-ы)              │ logs.txt │
                    ├───────────────────────┼──────────┤
                    │ Начальная дата        │ -        │
                    ├───────────────────────┼──────────┤
                    │ Конечная дата         │ -        │
                    ├───────────────────────┼──────────┤
                    │ Количество запросов   │ 20       │
                    ├───────────────────────┼──────────┤
                    │ Средний размер ответа │ 313b     │
                    └───────────────────────┴──────────┘
                    """
            ),
            Arguments.of(
                new MarkdownTablePrinter(),
                """
                    | Метрика               | Значение |
                    |:----------------------|:---------|
                    | Файл(-ы)              | logs.txt |
                    | Начальная дата        | -        |
                    | Конечная дата         | -        |
                    | Количество запросов   | 20       |
                    | Средний размер ответа | 313b     |
                    """
            ),
            Arguments.of(
                new AdocTablePrinter(),
                """
                    [options="header", cols="<,<"]
                    |===
                    | Метрика               | Значение\s
                    | Файл(-ы)              | logs.txt\s
                    | Начальная дата        | -       \s
                    | Конечная дата         | -       \s
                    | Количество запросов   | 20      \s
                    | Средний размер ответа | 313b    \s
                    |===
                    """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("printersResourceInfo")
    @SuppressWarnings("MagicNumber")
    public void printTableResourceInfoTest(TablePrinter printer, String expected) {
        String actual = printer.printTable(tables.get(1));
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> printersResourceInfo() {
        return Stream.of(
            Arguments.of(
                new TxtTablePrinter(),
                """
                    ┌──────────────────────┬────────────┐
                    │ Ресурс               │ Количество │
                    ╞══════════════════════╪════════════╡
                    │ /downloads/product_1 │ 13         │
                    ├──────────────────────┼────────────┤
                    │ /downloads/product_2 │ 7          │
                    └──────────────────────┴────────────┘
                    """
            ),
            Arguments.of(
                new MarkdownTablePrinter(),
                """
                    | Ресурс               | Количество |
                    |:---------------------|:-----------|
                    | /downloads/product_1 | 13         |
                    | /downloads/product_2 | 7          |
                    """
            ),
            Arguments.of(
                new AdocTablePrinter(),
                """
                    [options="header", cols="<,<"]
                    |===
                    | Ресурс               | Количество\s
                    | /downloads/product_1 | 13        \s
                    | /downloads/product_2 | 7         \s
                    |===
                    """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("printersResponseInfo")
    @SuppressWarnings("MagicNumber")
    public void printTableResponseInfoTest(TablePrinter printer, String expected) {
        String actual = printer.printTable(tables.get(2));
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> printersResponseInfo() {
        return Stream.of(
            Arguments.of(
                new TxtTablePrinter(),
                """
                    ┌─────┬──────────────┬────────────┐
                    │ Код │ Имя          │ Количество │
                    ╞═════╪══════════════╪════════════╡
                    │ 304 │ Not Modified │ 11         │
                    ├─────┼──────────────┼────────────┤
                    │ 404 │ Not Found    │ 6          │
                    ├─────┼──────────────┼────────────┤
                    │ 200 │ OK           │ 3          │
                    └─────┴──────────────┴────────────┘
                    """
            ),
            Arguments.of(
                new MarkdownTablePrinter(),
                """
                    | Код | Имя          | Количество |
                    |:----|:-------------|:-----------|
                    | 304 | Not Modified | 11         |
                    | 404 | Not Found    | 6          |
                    | 200 | OK           | 3          |
                    """
            ),
            Arguments.of(
                new AdocTablePrinter(),
                "[options=\"header\", cols=\"<,<,<\"]\n" +
                    "|===\n" +
                    "| Код | Имя          | Количество \n" +
                    "| 304 | Not Modified | 11         \n" +
                    "| 404 | Not Found    | 6          \n" +
                    "| 200 | OK           | 3          \n" +
                    "|===\n"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("printersClientInfo")
    @SuppressWarnings("MagicNumber")
    public void printTableClientInfoTest(TablePrinter printer, String expected) {
        String actual = printer.printTable(tables.get(3));
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> printersClientInfo() {
        return Stream.of(
            Arguments.of(
                new TxtTablePrinter(),
                """
                    ┌──────────────┬────────────┬─────────────────────┐
                    │ IP-адрес     │ Количество │ Последний запрос    │
                    ╞══════════════╪════════════╪═════════════════════╡
                    │ 93.180.71.3  │ 6          │ 17.05.2015 08:05:57 │
                    ├──────────────┼────────────┼─────────────────────┤
                    │ 217.168.17.5 │ 5          │ 17.05.2015 08:05:42 │
                    ├──────────────┼────────────┼─────────────────────┤
                    │ 80.91.33.133 │ 4          │ 17.05.2015 08:05:24 │
                    └──────────────┴────────────┴─────────────────────┘
                    """
            ),
            Arguments.of(
                new MarkdownTablePrinter(),
                """
                    | IP-адрес     | Количество | Последний запрос    |
                    |:-------------|:-----------|:--------------------|
                    | 93.180.71.3  | 6          | 17.05.2015 08:05:57 |
                    | 217.168.17.5 | 5          | 17.05.2015 08:05:42 |
                    | 80.91.33.133 | 4          | 17.05.2015 08:05:24 |
                    """
            ),
            Arguments.of(
                new AdocTablePrinter(),
                """
                    [options="header", cols="<,<,<"]
                    |===
                    | IP-адрес     | Количество | Последний запрос   \s
                    | 93.180.71.3  | 6          | 17.05.2015 08:05:57\s
                    | 217.168.17.5 | 5          | 17.05.2015 08:05:42\s
                    | 80.91.33.133 | 4          | 17.05.2015 08:05:24\s
                    |===
                    """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("printersHourInfo")
    @SuppressWarnings("MagicNumber")
    public void printTableHourInfoTest(TablePrinter printer, String expected) {
        String actual = printer.printTable(tables.get(4));
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> printersHourInfo() {
        return Stream.of(
            Arguments.of(
                new TxtTablePrinter(),
                """
                    ┌─────┬────────────┬─────────┐
                    │ Час │ Количество │ Процент │
                    ╞═════╪════════════╪═════════╡
                    │ 00  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 01  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 02  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 03  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 04  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 05  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 06  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 07  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 08  │ 20         │ 100%    │
                    ├─────┼────────────┼─────────┤
                    │ 09  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 10  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 11  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 12  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 13  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 14  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 15  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 16  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 17  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 18  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 19  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 20  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 21  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 22  │ 0          │ 0%      │
                    ├─────┼────────────┼─────────┤
                    │ 23  │ 0          │ 0%      │
                    └─────┴────────────┴─────────┘
                    """
            ),
            Arguments.of(
                new MarkdownTablePrinter(),
                """
                    | Час | Количество | Процент |
                    |:----|:-----------|:--------|
                    | 00  | 0          | 0%      |
                    | 01  | 0          | 0%      |
                    | 02  | 0          | 0%      |
                    | 03  | 0          | 0%      |
                    | 04  | 0          | 0%      |
                    | 05  | 0          | 0%      |
                    | 06  | 0          | 0%      |
                    | 07  | 0          | 0%      |
                    | 08  | 20         | 100%    |
                    | 09  | 0          | 0%      |
                    | 10  | 0          | 0%      |
                    | 11  | 0          | 0%      |
                    | 12  | 0          | 0%      |
                    | 13  | 0          | 0%      |
                    | 14  | 0          | 0%      |
                    | 15  | 0          | 0%      |
                    | 16  | 0          | 0%      |
                    | 17  | 0          | 0%      |
                    | 18  | 0          | 0%      |
                    | 19  | 0          | 0%      |
                    | 20  | 0          | 0%      |
                    | 21  | 0          | 0%      |
                    | 22  | 0          | 0%      |
                    | 23  | 0          | 0%      |
                    """
            ),
            Arguments.of(
                new AdocTablePrinter(),
                """
                    [options="header", cols="<,<,<"]
                    |===
                    | Час | Количество | Процент\s
                    | 00  | 0          | 0%     \s
                    | 01  | 0          | 0%     \s
                    | 02  | 0          | 0%     \s
                    | 03  | 0          | 0%     \s
                    | 04  | 0          | 0%     \s
                    | 05  | 0          | 0%     \s
                    | 06  | 0          | 0%     \s
                    | 07  | 0          | 0%     \s
                    | 08  | 20         | 100%   \s
                    | 09  | 0          | 0%     \s
                    | 10  | 0          | 0%     \s
                    | 11  | 0          | 0%     \s
                    | 12  | 0          | 0%     \s
                    | 13  | 0          | 0%     \s
                    | 14  | 0          | 0%     \s
                    | 15  | 0          | 0%     \s
                    | 16  | 0          | 0%     \s
                    | 17  | 0          | 0%     \s
                    | 18  | 0          | 0%     \s
                    | 19  | 0          | 0%     \s
                    | 20  | 0          | 0%     \s
                    | 21  | 0          | 0%     \s
                    | 22  | 0          | 0%     \s
                    | 23  | 0          | 0%     \s
                    |===
                    """
            )
        );
    }
}
