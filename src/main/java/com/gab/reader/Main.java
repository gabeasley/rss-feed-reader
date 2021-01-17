package com.gab.reader;

import com.gab.reader.model.dictionary.Dictionary;
import com.gab.reader.model.rss.Feed;
import com.gab.reader.parser.DictionaryParser;
import com.gab.reader.parser.RSSFeedParser;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.gab.reader.constant.Constant.*;

public class Main {
    public static void main(String[] args) {
        DictionaryParser dictionaryParser = new DictionaryParser();
        Dictionary dictionary = dictionaryParser.createDictionary();
        RSSFeedParser rssFeedParser = new RSSFeedParser();
        getDaySinceLastActive(dictionary, rssFeedParser);
    }

    public static void getDaySinceLastActive(Dictionary dictionary, RSSFeedParser parser) {
        FileWriter fw = null;
        long dayLastActive = 0;
        try {
            fw = new FileWriter(RESOURCE_PATH + "results.txt");
            for(Map.Entry<String, List<String>> companyList: dictionary.getCompanies().entrySet()) {
                for(String rssURL : companyList.getValue()) {
                    Feed feed = parser.readFeed(rssURL);
                    Date pubDate = determineDateFormat(feed.getPubDate());
                    Date today = new Date();
                    dayLastActive = ChronoUnit.DAYS.between(pubDate.toInstant(), today.toInstant());
                    System.out.println("Feed: " + feed.getTitle() + " - days last active: " + dayLastActive);
                }
                fw.write("Company: " + companyList.getKey() + " - days last active: " + dayLastActive + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("There was an error writing the results");
            e.printStackTrace();
        }
    }

    public static Date determineDateFormat(String pubDate) {
        List<String> formatStrings = Arrays.asList(NPR_DATE_FORMAT, NYT_DATEFORMAT);
        if(pubDate != null) {
            for (String formatString : formatStrings) {
                try {
                    return new SimpleDateFormat(formatString).parse(pubDate.replaceAll(",", ""));
                } catch (ParseException e) {}
            }
        }
        return null;
    }
}
