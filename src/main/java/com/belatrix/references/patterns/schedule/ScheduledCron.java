package com.belatrix.references.patterns.schedule;

import com.belatrix.references.patterns.exceptions.BusinessException;
import com.belatrix.references.patterns.exceptions.TechnicalException;
import com.belatrix.references.patterns.commands.PageProcessService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledCron {

    private final PageProcessService process;

    public ScheduledCron(PageProcessService process){
        this.process = process;
    }

    @Scheduled(cron = "${cron.expression}")
    public void processPages() throws TechnicalException, BusinessException {
        process.pageProcessInBackGround();
    }
}
