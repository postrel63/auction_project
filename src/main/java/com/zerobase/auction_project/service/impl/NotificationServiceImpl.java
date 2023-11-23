package com.zerobase.auction_project.service.impl;

import com.zerobase.auction_project.domain.Notification;
import com.zerobase.auction_project.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final Set<SseEmitter> emitters = ConcurrentHashMap.newKeySet();
    @Override
    public void addEmitter(String auctionId, SseEmitter sseEmitter) {
        this.emitters.add(sseEmitter);
    }

    @Override
    public void removeEmitter(String auctionId, SseEmitter sseEmitter) {
        this.emitters.remove(sseEmitter);
    }

    public void sendNotification(Notification notification) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name("notification").data(notification));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        });
        this.emitters.removeAll(deadEmitters);
    }
}
