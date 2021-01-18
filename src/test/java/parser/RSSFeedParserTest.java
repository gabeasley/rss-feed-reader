package parser;

import TestData.TestData;
import com.gab.reader.model.rss.Feed;
import com.gab.reader.parser.RSSFeedParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class RSSFeedParserTest {

    @InjectMocks
    private RSSFeedParser rssFeedParser;

    @Before
    public void setup() {
        rssFeedParser = new RSSFeedParser();
    }

    @Test(expected = NullPointerException.class)
    public void readFeedEmptyURLTest() {
        String emptyURL = "";
        rssFeedParser.readFeed(emptyURL);
    }

    @Test(expected = RuntimeException.class)
    public void readFeedMalformedURLTest() {
        String emptyURL = "mock.com-sf/sd";
        rssFeedParser.readFeed(emptyURL);
    }

    @Test
    public void readFeedTest() throws MalformedURLException {
        URL testUrl = Paths.get("src",("/test/resources/testRSS.xml")).toUri().toURL();
        Feed actual = rssFeedParser.readFeed(testUrl.toString());
        Feed expected = TestData.testFeed();
        Assert.assertEquals(expected,actual);
    }
}
