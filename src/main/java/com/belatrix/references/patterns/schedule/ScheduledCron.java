package com.belatrix.references.patterns.schedule;

import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.services.PageProcessService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledCron {

    private final PageProcessService process;

    public ScheduledCron(PageProcessService process){
        this.process = process;
    }

    @Scheduled(cron = "${cron.expression}")
    public void processPages() throws Exception, TechnicalException {
        process.pageProcessInBackGround();
    }
}
