package edu.project3;

import edu.project3.io.read.Config;
import edu.project3.io.read.LogReader;
import edu.project3.io.read.LogsData;
import edu.project3.log.HttpResponse;
import edu.project3.log.IllegalLogException;
import edu.project3.log.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class LogReaderTest {
    private static final String LOGS_PATH = "src/main/resources/project3/logs.txt";
    private static final String BAD_LOGS_PATH = "src/main/resources/project3/bad_logs.txt";
    private final Config config;

    LogReaderTest() {
        this.config = new Config(LOGS_PATH, (LocalDate) null, null, null);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    void readTest() throws IOException {
        LogsData expected = new LogsData(
            List.of(
                new Log("93.180.71.3", "-", LocalDateTime.parse("2015-05-17T08:05:32"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", null),
                new Log("93.180.71.3", "-", LocalDateTime.parse("2015-05-17T08:05:23"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", null),
                new Log("80.91.33.133", "-", LocalDateTime.parse("2015-05-17T08:05:24"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)", null),
                new Log("217.168.17.5", "-", LocalDateTime.parse("2015-05-17T08:05:34"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._200, 490, "-", "Debian APT-HTTP/1.3 (0.8.10.3)", null),
                new Log("217.168.17.5", "-", LocalDateTime.parse("2015-05-17T08:05:09"), "GET", "/downloads/product_2", "HTTP/1.1", HttpResponse._200, 490, "-", "Debian APT-HTTP/1.3 (0.8.10.3)", null),
                new Log("93.180.71.3", "-", LocalDateTime.parse("2015-05-17T08:05:57"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", null),
                new Log("217.168.17.5", "-", LocalDateTime.parse("2015-05-17T08:05:02"), "GET", "/downloads/product_2", "HTTP/1.1", HttpResponse._404, 337, "-", "Debian APT-HTTP/1.3 (0.8.10.3)", null),
                new Log("217.168.17.5", "-", LocalDateTime.parse("2015-05-17T08:05:42"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._404, 332, "-", "Debian APT-HTTP/1.3 (0.8.10.3)", null),
                new Log("80.91.33.133", "-", LocalDateTime.parse("2015-05-17T08:05:01"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)", null),
                new Log("93.180.71.3", "-", LocalDateTime.parse("2015-05-17T08:05:27"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", null),
                new Log("217.168.17.5", "-", LocalDateTime.parse("2015-05-17T08:05:12"), "GET", "/downloads/product_2", "HTTP/1.1", HttpResponse._200, 3316, "-", "-", null),
                new Log("188.138.60.101", "-", LocalDateTime.parse("2015-05-17T08:05:49"), "GET", "/downloads/product_2", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.9.7.9)", null),
                new Log("80.91.33.133", "-", LocalDateTime.parse("2015-05-17T08:05:14"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)", null),
                new Log("46.4.66.76", "-", LocalDateTime.parse("2015-05-17T08:05:45"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._404, 318, "-", "Debian APT-HTTP/1.3 (1.0.1ubuntu2)", null),
                new Log("93.180.71.3", "-", LocalDateTime.parse("2015-05-17T08:05:26"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._404, 324, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", null),
                new Log("91.234.194.89", "-", LocalDateTime.parse("2015-05-17T08:05:22"), "GET", "/downloads/product_2", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.9.7.9)", null),
                new Log("80.91.33.133", "-", LocalDateTime.parse("2015-05-17T08:05:07"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)", null),
                new Log("37.26.93.214", "-", LocalDateTime.parse("2015-05-17T08:05:38"), "GET", "/downloads/product_2", "HTTP/1.1", HttpResponse._404, 319, "-", "Go 1.1 package http", null),
                new Log("188.138.60.101", "-", LocalDateTime.parse("2015-05-17T08:05:25"), "GET", "/downloads/product_2", "HTTP/1.1", HttpResponse._304, 0, "-", "Debian APT-HTTP/1.3 (0.9.7.9)", null),
                new Log("93.180.71.3", "-", LocalDateTime.parse("2015-05-17T08:05:11"), "GET", "/downloads/product_1", "HTTP/1.1", HttpResponse._404, 340, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", null)),
            List.of("logs.txt"));
        LogsData actual = LogReader.read(config);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readEmptyTest() throws IOException {
        LogsData expected = new LogsData(List.of(), List.of());
        LogsData actual = LogReader.read(new Config("file.txt", (LocalDate) null, null, null));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readExceptionTest(){
        Assertions.assertThrows(
            IllegalLogException.class,
            () -> LogReader.read(new Config(BAD_LOGS_PATH, (LocalDate) null, null, null)));
    }
}
