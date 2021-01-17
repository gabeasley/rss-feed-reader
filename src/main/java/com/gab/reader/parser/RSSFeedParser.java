package com.gab.reader.parser;

import com.gab.reader.model.rss.Feed;
import com.gab.reader.model.rss.FeedMessage;
import org.apache.commons.lang3.StringUtils;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.gab.reader.constant.Constant.*;

public class RSSFeedParser {
    public Feed readFeed(String url) {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values initial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read(url);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, language,
                                        copyright, pubdate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart().equals(ITEM)) {
                        FeedMessage message = new FeedMessage();
                        message .setAuthor(author);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        message.setPubDate(pubdate);
                        feed.getMessages().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        if(StringUtils.isEmpty(feed.getPubDate())) {
            try {
                Date closestDate = null;
                for(FeedMessage feedMessage : feed.getMessages()) {
                    if(closestDate == null || (new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss zzz", Locale.US)
                            .parse(feedMessage.getPubDate().replaceAll(",", "")).compareTo(closestDate) > 0)) {
                        closestDate = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss zzz", Locale.US)
                                .parse(feedMessage.getPubDate().replaceAll(",", ""));
                    }
                }
                return new Feed(feed.getTitle(), feed.getLink(), feed.getDescription(), feed.getLanguage(),
                        feed.getCopyright(), closestDate.toString());
            } catch (ParseException e) {
                return new Feed(feed.getTitle(), feed.getLink(), feed.getDescription(), feed.getLanguage(),
                        feed.getCopyright(), "N/A");
            }
        }
        return feed;
    }

    private InputStream read(String feedUrl) {
        URL url = null;
        if(StringUtils.isEmpty(feedUrl)) {
            System.err.println("There is no rss url for this company");
        } else {
            try {
                url = new URL(feedUrl);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return url.openStream();
        } catch (IOException e) {
            System.err.println("There was an issue with this url");
            throw new RuntimeException(e);
        }

    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
}
