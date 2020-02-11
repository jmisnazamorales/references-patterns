package com.belatrix.references.patterns.services.impl;

import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.URLProcess;
import com.belatrix.references.patterns.services.FileReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileReaderServiceImpl implements FileReaderService {

    @Override
    public URLProcess readFilesFromDisk(String folder) throws TechnicalException {
        log.info("method readFilesFromDisk params {}", folder);
        List<String> filesInDirectory = readFilesInDirectory(folder);
        URLProcess process = new URLProcess();
        filesInDirectory.forEach(x ->
        {
            try (Stream<String> stream = Files.lines(Paths.get(x))){
                stream.forEach( u -> {
                    try {
                        process.getSuccessList().add(new URL(u));
                    } catch (MalformedURLException e) {
                        process.getWrongList().add(u);
                        log.error("error: {}", e.getMessage());
                    }
                });
            }catch (IOException e ){
                log.error("Error reading urls {}", e.getMessage());
            }
        });
        return process;
    }

    /**
     *
     * @param folder
     *        Folder to find files
     * @return
     *       Page list to
     */
    private static List<String> readFilesInDirectory(String folder) throws TechnicalException {
        List<String> result;
        try (Stream<Path> walk = Files.walk(Paths.get(folder))){
            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            throw new TechnicalException("Error reading files " + e.getMessage());
        }
        return result;
    }
}
