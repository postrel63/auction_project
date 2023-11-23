package com.zerobase.auction_project.notification.domain;

import com.zerobase.auction_project.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String url;

    private LocalDateTime timestamp;

    private Boolean isRead;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




}
