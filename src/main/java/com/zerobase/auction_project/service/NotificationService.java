package com.zerobase.auction_project.service;

import com.zerobase.auction_project.domain.Notification;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public interface NotificationService {
    void addEmitter(String auctionId, SseEmitter sseEmitter);

    void removeEmitter(String auctionId, SseEmitter sseEmitter);

    void sendNotification(Notification notification);
}
