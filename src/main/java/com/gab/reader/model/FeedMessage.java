package com.gab.reader.model;

import lombok.Data;

@Data
public class FeedMessage {
        public String title;
        public String description;
        public String link;
        public String author;
        public String guid;

        @Override
        public String toString() {
                return "FeedMessage [title=" + title + ", description=" + description
                        + ", link=" + link + ", author=" + author + ", guid=" + guid
                        + "]";
        }
}
