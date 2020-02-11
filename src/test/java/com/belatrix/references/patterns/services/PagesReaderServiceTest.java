package com.belatrix.references.patterns.services;

import com.belatrix.references.patterns.commands.Command;
import com.belatrix.references.patterns.commands.impl.CommandPagesReaderImpl;
import com.belatrix.references.patterns.commands.impl.CommandTextMatchImpl;
import com.belatrix.references.patterns.models.FileConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
class PagesReaderServiceTest {


    @InjectMocks
    private CommandPagesReaderImpl pagesReaderService;

    @InjectMocks
    private CommandTextMatchImpl textMatchService;

    private Command command;

    private Command commandTextMatch;

    private URL url;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        try {
           url = new URL("https://www.cbsnews.com/");
           command = new CommandPagesReaderImpl(url);
        }catch (Exception e){
            log.error("Error: malformed url {}", e.getMessage());
        }
    }

    @Test
    public void readPageTest(){
        try {
            String val = (String)command.call();
            Assert.assertNotNull(val);
        }catch (Exception  e ){
            log.error(e.getMessage());
        }
    }

    @Test
    public void textMatchTest(){
        try {
            String val = (String)command.call();
            commandTextMatch = new CommandTextMatchImpl(val, FileConstants.PATTERN_TAG);
            List<String> data = (List<String>) commandTextMatch.call();
            log.info(data.toString());
            Assert.assertEquals(data.get(0), null);
        }catch (Exception  e ){
            log.error(e.getMessage());
        }
    }
}