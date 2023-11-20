package com.zerobase.auction_project.components;

import com.zerobase.auction_project.domain.Bid;
import com.zerobase.auction_project.service.AuctionService;
import com.zerobase.auction_project.service.BidService;
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
    private final BidService bidService;


    /**
     *
     * @param context
     * @throws JobExecutionException
     *
     * 할 일
     * 1. 경매 종료
     * 2. 낙찰자 선정
     * 3. 낙찰자및 유찰자에게 알림 발송
     */


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("경매 시간 지난 경매 종료하는 메서드");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Long auctionId = Long.valueOf(jobDataMap.getString("auctionId"));

        //경매 종료
        auctionService.endAuction(auctionId);
        //낙찰자 정보
        Bid bid = bidService.bidUser(auctionId);

    }
}
