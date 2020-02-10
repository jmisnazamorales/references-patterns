package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.services.impl.FileWriterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class FileWriterServiceTest {

    @InjectMocks
    private FileWriterServiceImpl fileWriterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void writeFilesToDisk(){
        try {
            List<String> words = new ArrayList(){{add("data1");add("data2");add("data3");add("data4");add("data5");}};
            fileWriterService.writeOutPutFile(words);
        }catch (Exception  e ){
            log.error(e.getMessage());
        }
    }
}