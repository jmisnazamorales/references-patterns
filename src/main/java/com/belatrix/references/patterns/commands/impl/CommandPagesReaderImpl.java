package com.belatrix.references.patterns.commands.impl;

import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.models.FileConstants;
import com.belatrix.references.patterns.commands.CommandPagesReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;

@Slf4j
public class CommandPagesReaderImpl implements CommandPagesReader {

    private URL url;

    public CommandPagesReaderImpl(URL url){
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
                throw new Exception(e.getMessage());
            }
            return selectUtilText(sb.toString());
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


    private String selectUtilText(String completeText){
        StringBuilder sb = new StringBuilder();
        final Matcher matcher = FileConstants.TAG_REGEX.matcher(completeText);
        while (matcher.find()) {
            sb.append(matcher.group(1));
        }
        String val =completeText.replace(sb.toString(), "") ;
        return selectUtilTextScript(val);
        //return completeText.replace(sb.toString(), "") ;
    }

    private String selectUtilTextScript(String completeText){
        StringBuilder sb = new StringBuilder();
        final Matcher matcherScript = FileConstants.TAG_REGEX_SCRIPT.matcher(completeText);
        while (matcherScript.find()) {
            sb.append(matcherScript.group(1));
        }
        return sb.toString();
    }

}
