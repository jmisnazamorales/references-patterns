package com.belatrix.references.patterns.commands.concrete;

import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.FileConstants;
import com.belatrix.references.patterns.services.PagesReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;

@Slf4j
public class PagesReaderCommandService implements PagesReaderService {

    private URL url;

    public PagesReaderCommandService(URL url){
        this.url = url;
    }

    @Override
    public String call() throws Exception{
        log.info("method readPage params {}", url.toString());
        int status;
        try {
            status = getConnectionCodeUrl(url);
        }catch (TechnicalException e){
            log.error("Error: {}", e.getMessage());
            throw new Exception("Error: " + e.getMessage());
        }
        if(HttpStatus.OK.value() == status){
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
            } catch (IOException e) {
                log.error("Error io {}", e.getMessage());
                e.printStackTrace();
            }
            return sb.toString();
        }
        else{
            throw new Exception("Status no valid : " + status + " for page :" + url);
        }
    }

    private int getConnectionCodeUrl(URL url) throws TechnicalException {
        log.info("test connection {}", url);
        try {
            return ((HttpURLConnection) url.openConnection()).getResponseCode();
        }catch (IOException e){
            throw new TechnicalException("Cannot open url. Reason :" + e.getMessage());
        }
    }


    private String extractUtilText(String completeText){
        StringBuilder sb = new StringBuilder();
        final Matcher matcher = FileConstants.TAG_REGEX.matcher(completeText);
        while (matcher.find()) {
            sb.append(matcher.group(1));
        }
        return sb.toString();
    }

}
