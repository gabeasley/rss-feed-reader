package com.gab.reader;

import com.gab.reader.model.Feed;
import com.gab.reader.model.FeedMessage;
import com.gab.reader.parser.RSSFeedParser;

public class Main {
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://feeds.npr.org/510312/podcast.xml");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
        }
    }
}
