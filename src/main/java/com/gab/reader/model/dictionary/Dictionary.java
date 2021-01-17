package com.gab.reader.model.dictionary;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Dictionary {
    HashMap<String, List> companies = new HashMap<>();
}
