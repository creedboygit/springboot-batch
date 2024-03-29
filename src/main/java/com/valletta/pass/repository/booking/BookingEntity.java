package com.valletta.pass.repository.booking;

import com.valletta.pass.repository.BaseEntity;
import com.valletta.pass.repository.pass.PassEntity;
import com.valletta.pass.repository.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_seq")
    private Integer bookingSeq;

    @Column(name = "pass_seq")
    private Integer passSeq;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "used_pass")
    private boolean usedPass;

    private boolean attended;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    @Exclude
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "passSeq", insertable = false, updatable = false)
    @JoinColumn(name = "pass_seq", insertable = false, updatable = false)
//    @Exclude
    private PassEntity passEntity;

    // endedAt 기준, yyyy-MM-HH 00:00:00
    public LocalDateTime getStatisticsAt() {
        return this.endedAt.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

}
