package com.zerobase.auction_project.components;

import com.zerobase.auction_project.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuctionEndJob extends QuartzJobBean {

    private final AuctionService auctionService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("경매 시간 지난 경매 종료하는 메서드");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Long auctionId = Long.valueOf(jobDataMap.getString("auctionId"));

        auctionService.endAuction(auctionId);
    }
}
