package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.URLProcess;
import com.belatrix.references.patterns.services.impl.FileReaderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

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
            URLProcess text = fileReaderService.readFilesFromDisk(directory);
            Assert.assertNotNull(text.getSuccessList().get(0));
        }catch (Exception | TechnicalException e ){
            log.error(e.getMessage());
        }
    }
}