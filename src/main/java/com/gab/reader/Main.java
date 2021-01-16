package com.gab.reader;

import com.gab.reader.model.Feed;
import com.gab.reader.model.FeedMessage;
import com.gab.reader.parser.RSSFeedParser;

public class Main {
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
        }
    }
}
