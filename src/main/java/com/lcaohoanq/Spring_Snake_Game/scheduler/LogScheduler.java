package com.lcaohoanq.Spring_Snake_Game.scheduler;

import com.lcaohoanq.Spring_Snake_Game.service.LogToExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogScheduler {

    @Autowired
    private LogToExcelService logToExcelService;

    // Schedule the task to run every week
    @Scheduled(cron = "0 0 0 * * SUN") // Every Sunday at midnight
    public void scheduleLogToExcelTask() {
        logToExcelService.generateExcelFromLogs();
    }
}

