package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.commands.impl.CommandPagesReaderImpl;
import com.belatrix.references.patterns.commands.impl.CommandTextMatchImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URL;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
class PagesReaderServiceTest {


    @InjectMocks
    private CommandPagesReaderImpl pagesReaderService;

    @InjectMocks
    private CommandTextMatchImpl textMatchService;

    private URL url;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        try {
           url = new URL("https://www.boston.com/");
        }catch (Exception e){
            log.error("Error: malformed url {}", e.getMessage());
        }
    }

    @Test
    public void readPageTest(){
        try {
            String text = pagesReaderService.call();
            log.info(text);
        }catch (Exception  e ){
            log.error(e.getMessage());
        }

    }
}