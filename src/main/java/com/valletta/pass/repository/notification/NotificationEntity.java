package com.valletta.pass.repository.notification;

import com.valletta.pass.repository.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_seq")
    private Integer notificationSeq;
    private String uuid;

    private NotificationEvent event;
    private String text;
    private boolean sent;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}
