package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.exceptions.TechnicalException;

import java.util.List;

/**
 *
 */
public interface FileWriterService{

    void writeOutPutFile(List<String> words, String path) throws TechnicalException;

}
