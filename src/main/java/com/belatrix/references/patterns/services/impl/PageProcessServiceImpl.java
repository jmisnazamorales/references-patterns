package com.belatrix.references.patterns.services.impl;

import com.belatrix.references.patterns.commands.Command;
import com.belatrix.references.patterns.commands.impl.CommandPagesReaderImpl;
import com.belatrix.references.patterns.commands.impl.CommandTextMatchImpl;
import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.FileConstants;
import com.belatrix.references.patterns.models.URLProcess;
import com.belatrix.references.patterns.services.FileReaderService;
import com.belatrix.references.patterns.services.FileWriterService;
import com.belatrix.references.patterns.services.PageProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class PageProcessServiceImpl implements PageProcessService {

    @Value("${text.input.reader}")
    private String path;

    @Value("${text.output.reader}")
    private String pathOutPut;

    @Value("${text.output.failures}")
    private String pathOutPutFailures;


    private ExecutorService executor;
    private final FileReaderService fileReaderService;
    private final FileWriterService fileWriterService;

    public PageProcessServiceImpl(FileReaderService fileReaderService, FileWriterService fileWriterService){
        this.fileReaderService = fileReaderService;
        this.fileWriterService = fileWriterService;
    }

    @Override
    public void pageProcessInBackGround() throws Exception, TechnicalException {
        log.info("Processing start {}", new Date());
        executor = Executors.newCachedThreadPool();
        try {
            URLProcess dataProcess = fileReaderService.readFilesFromDisk(path);
            List<URL> pages = dataProcess.getSuccessList();
            pages.parallelStream().forEach( x ->
            {
                try {
                    log.info("Sending {}", x);
                    Command<String> commandReader = new CommandPagesReaderImpl(x);
                    Future<String> future =  executor.submit(commandReader);
                    while(!future.isDone()) {
                    }
                    String result = future.get();
                    Command commandTextMatch = new CommandTextMatchImpl(result, FileConstants.PATTERN_TAG);
                    Future<List<String>> futureTextMatch = executor.submit(commandTextMatch);
                    while(!futureTextMatch.isDone()) {
                    }
                    log.info("Finish match");
                    List<String> listResult = futureTextMatch.get();
                    fileWriterService.writeOutPutFile(listResult, pathOutPut + getNameFileOutPut(x.toString()));
                }catch (InterruptedException | ExecutionException | TechnicalException e ){
                    dataProcess.getWrongList().add(x.toString());
                    log.error("Error executing: {}", e.getMessage());
                }
            });
            writeWrongPage(dataProcess);
        }
        catch (Exception | TechnicalException e ){
            throw e;
        }
        finally {
            executor.shutdown();
        }
        log.info("Processing finish {}", new Date());
    }

    private String getNameFileOutPut(String url){
        return new StringBuilder(url.split(FileConstants.DELIMITER_NAME)[1])
                .append(FileConstants.FILE_EXTENSION).toString();
    }

    private void writeWrongPage(URLProcess dataProcess) throws TechnicalException {
        log.info("Writing wrong pages {}", dataProcess.getWrongList());
        if(!dataProcess.getWrongList().isEmpty()){
            try {
                fileWriterService.writeOutPutFile(dataProcess.getWrongList(),
                        pathOutPutFailures + FileConstants.FILE_FAILURES);
            }catch (TechnicalException e){
                throw new TechnicalException("Cannot write failure file");
            }

        }
    }

}
