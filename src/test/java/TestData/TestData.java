package TestData;

import com.gab.reader.model.dictionary.Dictionary;
import com.gab.reader.model.rss.Feed;
import com.gab.reader.model.rss.FeedMessage;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static Feed testFeed() {
        List<FeedMessage> entries = new ArrayList<>();
        FeedMessage feedMessage = new FeedMessage();
        feedMessage.setTitle("These 4 Schools Created Outdoor Classrooms. Take a Look.");
        feedMessage.setDescription("To combat the coronavirus, schools across America moved students outdoors. Here’s a look at four new learning environments.");
        feedMessage.setGuid("https://www.nytimes.com/2020/10/27/us/outdoor-classroom-design.html");
        feedMessage.setPubDate("Fri, 20 Nov 2020 18:16:40 +0000");
        feedMessage.setAuthor("");
        feedMessage.setLink("");
        entries.add(feedMessage);
        Feed feed = new Feed("NYT > Education","https://www.nytimes.com/section/education","","en-us",
                "Copyright 2021 The New York Times Company","Mon, 18 Jan 2021 20:52:54 +0000");
        feed.getMessages().addAll(entries);
        return feed;
    }

    public static Dictionary testDictionary() {
        Dictionary dictionary = new Dictionary();
        String url1 = "https://rss.nytimes.com/services/xml/rss/nyt/Americas.xml";
        String url2 = "https://rss.nytimes.com/services/xml/rss/nyt/World.xml";
        List<String> company1 = new ArrayList<>();
        company1.add(url1);
        company1.add(url2);
        String url3 = "http://feeds.bbci.co.uk/news/rss.xml";
        List<String> company2 = new ArrayList<>();
        company2.add(url3);
        dictionary.addCompany("THE NEW YORK TIMES",company1);
        dictionary.addCompany("BBC",company2);
        return dictionary;
    }
}
