package com.gab.reader;

import com.gab.reader.model.dictionary.Dictionary;
import com.gab.reader.model.rss.Feed;
import com.gab.reader.parser.DictionaryParser;
import com.gab.reader.parser.RSSFeedParser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.gab.reader.constant.Constant.RESOURCE_PATH;

public class Main {
    public static void main(String[] args) {
        DictionaryParser dictionaryParser = new DictionaryParser();
        Dictionary dictionary = dictionaryParser.createDictionary();
        RSSFeedParser rssFeedParser = new RSSFeedParser();
        getDaySinceLastActive(dictionary, rssFeedParser);
    }
//        RSSFeedParser parser = new RSSFeedParser(
//                "https://feeds.npr.org/1095/podcast.xml");
//        Feed feed = parser.readFeed();
//        System.out.println(feed);
//        for (FeedMessage message : feed.getMessages()) {
//            System.out.println(message);
//        }

    public static void getDaySinceLastActive(Dictionary dictionary, RSSFeedParser parser) {
        FileWriter fw= null;
        int dayLastActive = 0;
        try {
            fw = new FileWriter(RESOURCE_PATH + "results.txt");
            for(Map.Entry<String, List<String>> companyList: dictionary.getCompanies().entrySet()) {
                for(String rssURL : companyList.getValue()) {
                    Feed feed = parser.readFeed(rssURL);
                    System.out.println("Feed: " + feed.getTitle() + " - days last active: " + feed.getPubDate());
                }
                fw.write("Company: " + companyList.getKey() + " - days last active: " + dayLastActive + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("There was an error writing the results");
            e.printStackTrace();
        }
    }
}
