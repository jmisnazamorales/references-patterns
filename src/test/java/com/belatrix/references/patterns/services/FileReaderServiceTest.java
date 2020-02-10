package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.services.impl.FileReaderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URL;
import java.util.List;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class FileReaderServiceTest {


    @InjectMocks
    private FileReaderServiceImpl fileReaderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readFilesFromDisk(){
        try {
            final String directory = "C:\\input_reader";
            List<URL> text = fileReaderService.readFilesFromDisk(directory);
            log.info(text.toString());
        }catch (Exception  e ){
            log.error(e.getMessage());
        }
    }
}