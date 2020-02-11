package com.belatrix.references.patterns.commands.impl;

import com.belatrix.references.patterns.commands.CommandTextMatch;
import com.belatrix.references.patterns.models.FileConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CommandTextMatchImpl implements CommandTextMatch {

    private String text;

    private String pattern;

    public CommandTextMatchImpl(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
    }


    @Override
    public List<String> call() throws Exception {
        return Arrays.stream(text.split(FileConstants.DELIMITER))
                .filter(x -> x.matches(pattern)).distinct().sorted()
                .collect(Collectors.toList());
    }
}
