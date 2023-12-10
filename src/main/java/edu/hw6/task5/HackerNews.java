package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final String TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_FORMAT = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final Duration TIMEOUT = Duration.of(20, ChronoUnit.SECONDS);
    private static final String DELIMITER = ",";

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(TOP_STORIES)).GET().timeout(TIMEOUT).build();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            body = body.substring(1, body.length() - 1);
            return Arrays.stream(body.split(DELIMITER))
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public static String news(long id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(
            new URI(ITEM_FORMAT.formatted(id))).GET().timeout(TIMEOUT).build();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            return getTitle(responseBody);
        }
    }

    private static String getTitle(String json) {
        Pattern pattern = Pattern.compile("\"title\"\\s*:\\s*\"([^\"]+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
