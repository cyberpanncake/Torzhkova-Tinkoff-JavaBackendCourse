package edu.hw6;

import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

class Task5Test {

    @Test
    public void hackerNewsTopStoriesTest() throws URISyntaxException {
        long[] ids = HackerNews.hackerNewsTopStories();
        Assertions.assertNotNull(ids);
    }

    @Test
    public void newsTest() throws IOException, InterruptedException, URISyntaxException {
        long[] ids = HackerNews.hackerNewsTopStories();
        Assertions.assertNotNull(ids);
        int maxCount = 10;
        int i = 0;
        for (long id : ids) {
            Assertions.assertNotNull(HackerNews.news(id));
            if (++i == maxCount) {
                break;
            }
        }
    }
}
