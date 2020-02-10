package com.belatrix.references.patterns.commands;

import com.belatrix.references.patterns.commands.Command;

import java.util.List;

/**
 *
 */
public interface TextMatchService extends Command<List<String>> {

    /**
     * Find word that make match with selected pattern
     * @param text
     *        Text to analyze
     * @param pattern
     *        Pattern
     * @return
     */
    //List<String> getMatchesInText(String text, String pattern);
}
