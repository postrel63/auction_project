package com.zerobase.auction_project.configuration;

import com.zerobase.auction_project.components.AuctionEndJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobConfig {

    private final Scheduler scheduler;

    @PostConstruct
    public void start() {
        JobDetail jobDetail = buildJobDetail(AuctionEndJob.class, new HashMap<>(), "myJob", "group1");

        try {
            scheduler.scheduleJob(jobDetail, buildJobTrigger("0/20 * * * * ?", "myTrigger", "group1"));
        } catch (SchedulerException e) {
            log.error("Error scheduling job", e);
        }
    }

    public Trigger buildJobTrigger(String scheduleExp, String name, String group) {
        return TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp))
                .startNow()
                .build();
    }

    public JobDetail buildJobDetail(Class<? extends Job> jobClass, Map<String, ?> params,
                                    String name, String group) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return JobBuilder.newJob(jobClass)
                .withIdentity(name, group)
                .usingJobData(jobDataMap)
                .build();
    }
}