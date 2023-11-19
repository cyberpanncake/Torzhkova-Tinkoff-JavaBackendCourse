package edu.project3;

import edu.project3.analysis.LogAnalyser;
import edu.project3.analysis.info.ClientInfo;
import edu.project3.analysis.info.CommonInfo;
import edu.project3.analysis.info.HourInfo;
import edu.project3.analysis.info.ResourceInfo;
import edu.project3.analysis.info.ResponseInfo;
import edu.project3.io.read.Config;
import edu.project3.log.HttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class LogAnalyserTest {
    private static final String LOGS_PATH = "src/main/resources/project3/logs.txt";
    private static final int TOP_SIZE = 3;
    private final LogAnalyser analyser;

    LogAnalyserTest() throws IOException {
        Config config = new Config(LOGS_PATH, (LocalDate) null, null, null);
        this.analyser = new LogAnalyser(config);
    }

    @Test
    void commonInfoTest() {
        List<CommonInfo> expected = List.of(
            new CommonInfo("Файл(-ы)", "logs.txt"),
            new CommonInfo("Начальная дата", "-"),
            new CommonInfo("Конечная дата", "-"),
            new CommonInfo("Количество запросов", "20"),
            new CommonInfo("Средний размер ответа", "313b")
        );
        List<CommonInfo> actual = analyser.getCommonInfo();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMostPopularResourcesTest() {
        List<ResourceInfo> expected = List.of(
            new ResourceInfo("/downloads/product_1", 13),
            new ResourceInfo("/downloads/product_2", 7)
        );
        List<ResourceInfo> actual = analyser.getMostPopularResources(TOP_SIZE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMostPopularResponsesTest() {
        List<ResponseInfo> expected = List.of(
            new ResponseInfo(HttpResponse._304, 11),
            new ResponseInfo(HttpResponse._404, 6),
            new ResponseInfo(HttpResponse._200, 3)
        );
        List<ResponseInfo> actual = analyser.getMostPopularResponses(TOP_SIZE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMostActiveClientsTest() {
        List<ClientInfo> expected = List.of(
            new ClientInfo("93.180.71.3", 6, LocalDateTime.parse("2015-05-17T08:05:57")),
            new ClientInfo("217.168.17.5", 5, LocalDateTime.parse("2015-05-17T08:05:42")),
            new ClientInfo("80.91.33.133", 4, LocalDateTime.parse("2015-05-17T08:05:24"))
        );
        List<ClientInfo> actual = analyser.getMostActiveClients(TOP_SIZE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getHoursInfoTest() {
        List<HourInfo> expected = List.of(
            new HourInfo(0, 0,0),
            new HourInfo(1, 0, 0),
            new HourInfo(2, 0, 0),
            new HourInfo(3, 0,0),
            new HourInfo(4, 0, 0),
            new HourInfo(5, 0, 0),
            new HourInfo(6, 0,0),
            new HourInfo(7, 0, 0),
            new HourInfo(8, 20, 1),
            new HourInfo(9, 0,0),
            new HourInfo(10, 0, 0),
            new HourInfo(11, 0, 0),
            new HourInfo(12, 0,0),
            new HourInfo(13, 0, 0),
            new HourInfo(14, 0, 0),
            new HourInfo(15, 0,0),
            new HourInfo(16, 0, 0),
            new HourInfo(17, 0, 0),
            new HourInfo(18, 0,0),
            new HourInfo(19, 0, 0),
            new HourInfo(20, 0, 0),
            new HourInfo(21, 0,0),
            new HourInfo(22, 0, 0),
            new HourInfo(23, 0, 0)
        );
        List<HourInfo> actual = analyser.getHoursInfo();
        Assertions.assertEquals(expected, actual);
    }
}
