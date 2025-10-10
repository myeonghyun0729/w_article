package com.w.article.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyScheduler {

    // @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "0 0/20 * * * ?")
    public void runDailyTask() {
        System.out.println("하루에 한 번 실행되는 작업: " + java.time.LocalDateTime.now());
    }
}
