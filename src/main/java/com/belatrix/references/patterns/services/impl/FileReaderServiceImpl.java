package com.belatrix.references.patterns.services.impl;

import com.belatrix.references.patterns.services.FileReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileReaderServiceImpl implements FileReaderService {

    @Override
    public List<URL> readFilesFromDisk(String folder) {
        log.info("method readFilesFromDisk params {}", folder);
        List<String> filesInDirectory = readFilesInDirectory(folder);
        List<URL> urls = new ArrayList<>();
        filesInDirectory.stream().forEach( x ->
        {
            try (Stream<String> stream = Files.lines(Paths.get(x))){
                stream.forEach( u -> {
                    try {
                        urls.add(new URL(u));
                    } catch (MalformedURLException e) {
                        log.error("error: {}", e.getMessage());
                    }
                });
            }catch (IOException e ){
                log.error("Error reading urls {}", e.getMessage());
            }
        });
        return urls;
    }

    /**
     *
     * @param folder
     * @return
     *       Page list to
     */
    private static List<String> readFilesInDirectory(String folder) {
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(folder))){
            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
