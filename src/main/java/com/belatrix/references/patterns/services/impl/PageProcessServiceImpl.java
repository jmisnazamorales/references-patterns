package com.belatrix.references.patterns.services.impl;

import com.belatrix.references.patterns.commands.Command;
import com.belatrix.references.patterns.commands.concrete.PagesReaderCommandService;
import com.belatrix.references.patterns.commands.concrete.TextMatchCommandService;
import com.belatrix.references.patterns.exceptions.BusinessException;
import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.FileConstants;
import com.belatrix.references.patterns.services.FileReaderService;
import com.belatrix.references.patterns.commands.PageProcessService;
import com.belatrix.references.patterns.services.FileWriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class PageProcessServiceImpl implements PageProcessService {

    @Value("${text.input.reader}")
    private String path;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private final FileReaderService fileReaderService;
    private final FileWriterService fileWriterService;

    public PageProcessServiceImpl(FileReaderService fileReaderService, FileWriterService fileWriterService){
        this.fileReaderService = fileReaderService;
        this.fileWriterService = fileWriterService;
    }

    @Override
    public void pageProcessInBackGround() throws BusinessException, TechnicalException {
        log.info("Processing start {}", new Date());
        try {
            List<URL> pages = fileReaderService.readFilesFromDisk(path);
            pages.parallelStream().forEach( x ->
            {
                try {
                    log.info("Sending {}", x);
                    Command<String> commandReader = new PagesReaderCommandService(x);
                    Future<String> future =  executor.submit(commandReader);
                    while(!future.isDone()) {
                    }
                    String result = future.get();
                    Command commandTextMatch = new TextMatchCommandService(result, FileConstants.PATTERN_TAG);
                    Future<List<String>> futureTextMatch = executor.submit(commandTextMatch);
                    while(!futureTextMatch.isDone()) {
                    }
                    log.info("Finish match");
                    List<String> listResult = futureTextMatch.get();
                    fileWriterService.writeOutPutFile(listResult);
                    log.info(futureTextMatch.get().toString());
                }catch (Exception e ){
                    log.error("Error executing: {}", e.getMessage());
                }
                finally {
                    //executor.shutdown();
                    //executorService.shutdown();
                }
            });
        }
        catch (Exception e ){
            log.error("Error : {}", e.getMessage());
        }
        log.info("Processing finish {}", new Date());
    }

}
