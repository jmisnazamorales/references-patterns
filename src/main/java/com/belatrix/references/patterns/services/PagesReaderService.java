package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.commands.Command;
import com.belatrix.references.patterns.exceptions.TechnicalException;

import java.net.URL;

/**
 *
 */
public interface PagesReaderService extends Command<String> {

    /**
     *Read data from main page
     * @param url
     *        resource url
     * @return
     *        Text in main page from url
     */
    //String readPage(URL url) throws TechnicalException;
}
