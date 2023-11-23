package com.zerobase.auction_project.notification.controller;

import com.zerobase.auction_project.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{auctionId}/notifications")
    public SseEmitter subscribe(@PathVariable String auctionId){
        SseEmitter sseEmitter = new SseEmitter();

        notificationService.addEmitter(auctionId, sseEmitter);
        sseEmitter.onCompletion(() -> notificationService.removeEmitter(auctionId,sseEmitter));
        sseEmitter.onTimeout(() -> notificationService.removeEmitter(auctionId, sseEmitter));
        return sseEmitter;
    }
}
