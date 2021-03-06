package com.gab.reader.model.rss;

import lombok.Data;

@Data
public class FeedMessage {
        public String title;
        public String description;
        public String link;
        public String author;
        public String guid;
        public String pubDate;

        @Override
        public String toString() {
                return "FeedMessage [title=" + title + ", description=" + description
                        + ", link=" + link + ", author=" + author + ", guid=" + guid
                        + "]";
        }
}
