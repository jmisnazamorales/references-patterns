package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.URLProcess;

public interface FileReaderService{

    URLProcess readFilesFromDisk(String folder) throws TechnicalException;
}
