package com.gab.reader.parser;

import com.gab.reader.model.dictionary.Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryParser {
    public Dictionary createDictionary(String dictionaryResource) {
        Dictionary companyDictionary = new Dictionary();
        try {
            File dictionaryFile = new File(dictionaryResource + "dictionary.txt");
            Scanner myReader = new Scanner(dictionaryFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] companyAndFeeds = data.split(",");
                if (companyAndFeeds.length > 1) {
                    List<String> urlFeeds = new ArrayList<>();
                    String company = companyAndFeeds[0].toUpperCase().trim();
                    if (!companyDictionary.getCompanies().containsKey(company)) {
                        for (int i = 1; i <= companyAndFeeds.length - 1; i++) {
                            urlFeeds.add(companyAndFeeds[i].trim());
                        }
                        companyDictionary.addCompany(companyAndFeeds[0].toUpperCase(), urlFeeds);
                    } else if (companyDictionary.getCompanies().containsKey(company)) {
                        for (int i = 1; i <= companyAndFeeds.length - 1; i++) {
                            if(!companyDictionary.getCompanies().get(company).contains(companyAndFeeds[i].trim())) {
                                urlFeeds.add(companyAndFeeds[i].trim());
                            }
                        }
                        companyDictionary.addUrlsToCompany(company, urlFeeds);
                    }
                }
                System.out.println(data);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return companyDictionary;
    }
}
