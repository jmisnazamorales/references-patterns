package com.belatrix.references.patterns.services;

import java.net.URL;
import java.util.List;

public interface FileReaderService{

    List<URL> readFilesFromDisk(String folder);
}
