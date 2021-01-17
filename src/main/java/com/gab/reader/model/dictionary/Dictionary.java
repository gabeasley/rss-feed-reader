package com.gab.reader.model.dictionary;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Dictionary {
    private HashMap<String, List<String>> companies = new HashMap<>();

    public void addCompany(String company, List<String> feeds) {
        companies.put(company, feeds);
    }

    public void addUrlToCompany(String company, String url) {
        companies.get(company).add(url);
    }

    public void addUrlsToCompany(String company, List<String> url) {
        companies.get(company).addAll(url);
    }
}
