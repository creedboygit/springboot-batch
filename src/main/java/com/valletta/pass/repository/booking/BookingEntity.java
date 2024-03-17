package com.valletta.pass.repository.booking;

import com.valletta.pass.repository.BaseEntity;
import com.valletta.pass.repository.pass.PassEntity;
import com.valletta.pass.repository.user.UserEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

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
