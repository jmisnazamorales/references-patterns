package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.models.URLProcess;

public interface FileReaderService{

    URLProcess readFilesFromDisk(String folder);
}
