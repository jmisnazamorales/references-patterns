package com.belatrix.references.patterns.services.impl;

import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.FileConstants;
import com.belatrix.references.patterns.services.FileWriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
public class FileWriterServiceImpl implements FileWriterService {

    @Override
    public void writeOutPutFile(List<String> words, String path) throws TechnicalException {
        Charset utf8 = StandardCharsets.UTF_8;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), utf8))) {
            for (String word : words) {
                writer.write(word + FileConstants.BREAK);
            }
        } catch (IOException e) {
            log.error("IOException: %s%n {}", e.getMessage());
            throw new TechnicalException("Cannot save file : {}", e.getCause());
        }
    }
}
