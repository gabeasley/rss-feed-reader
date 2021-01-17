package com.gab.reader;

import com.gab.reader.model.rss.Feed;
import com.gab.reader.model.rss.FeedMessage;
import com.gab.reader.parser.DictionaryParser;
import com.gab.reader.parser.RSSFeedParser;

public class Main {
    public static void main(String[] args) {
        DictionaryParser dictionaryParser = new DictionaryParser();
        dictionaryParser.createDictionary();
//        RSSFeedParser parser = new RSSFeedParser(
//                "https://feeds.npr.org/1095/podcast.xml");
//        Feed feed = parser.readFeed();
//        System.out.println(feed);
//        for (FeedMessage message : feed.getMessages()) {
//            System.out.println(message);
//        }
    }
}
