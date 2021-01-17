package com.gab.reader.parser;

import com.gab.reader.model.dictionary.Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.gab.reader.constant.Constant.RESOURCE_PATH;

public class DictionaryParser {
    public void createDictionary() {
        Dictionary companyDictionary = new Dictionary();
        try {
            File myObj = new File(RESOURCE_PATH + "dictionary.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
