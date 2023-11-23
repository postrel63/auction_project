package com.zerobase.auction_project.components;

import com.zerobase.auction_project.auction.domain.Auction;
import com.zerobase.auction_project.bid.domain.Bid;
import com.zerobase.auction_project.notification.domain.Notification;
import com.zerobase.auction_project.auction.service.AuctionService;
import com.zerobase.auction_project.bid.service.BidService;
import com.zerobase.auction_project.notification.service.NotificationService;
import com.zerobase.auction_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuctionEndJob extends QuartzJobBean {

    private final AuctionService auctionService;
    private final BidService bidService;
    private final NotificationService notificationService;
    private final UserService userService;


    /**
     * @param context
     * @throws JobExecutionException 할 일
     *                               1. 경매 종료
     *                               2. 낙찰자 선정
     *                               3. 낙찰자및 유찰자에게 알림 발송
     */


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("경매 시간 지난 경매 종료하는 메서드");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Long auctionId = Long.valueOf(jobDataMap.getString("auctionId"));

        Auction auction = auctionService.findByAuctionId(auctionId);
        //경매 종료
        auctionService.endAuction(auctionId);

        //낙찰자 정보
        Bid bid = bidService.bidUser(auctionId);

        Notification notify = Notification.builder()
                .isRead(false)
                .message(auction.getTitle() + " 경매가 종료되었습니다.")
                .timestamp(LocalDateTime.now())
                .user(auction.getUser())
                .build();
        notificationService.sendNotification(notify);
    }
}
