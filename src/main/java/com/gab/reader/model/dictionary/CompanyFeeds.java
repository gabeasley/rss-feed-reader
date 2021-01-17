package com.gab.reader.model.dictionary;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyFeeds {
    List<String> companyUrls = new ArrayList<>();
}
