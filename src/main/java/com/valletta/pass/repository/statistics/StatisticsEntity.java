package com.valletta.pass.repository.statistics;

import com.valletta.pass.repository.booking.BookingEntity;
import com.valletta.pass.repository.booking.BookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "statistics")
public class StatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_seq")
    private Integer statisticsSeq;

    @Column(name = "statistics_at")
    private LocalDateTime statisticsAt; // 일 단위

    @Column(name = "all_count")
    private int allCount;

    @Column(name = "attended_count")
    private int attendedCount;

    @Column(name = "canceled_count")
    private int canceledCount;

    public static StatisticsEntity create(final BookingEntity bookingEntity) {
        StatisticsEntity statisticsEntity = new StatisticsEntity();
        statisticsEntity.setStatisticsAt(bookingEntity.getStatisticsAt());
        statisticsEntity.setAllCount(1);

        if (bookingEntity.isAttended()) {
            statisticsEntity.setAttendedCount(1);
        }

        if (BookingStatus.CANCELED.equals(bookingEntity.getStatus())) {
            statisticsEntity.setCanceledCount(1);
        }

        return statisticsEntity;
    }

    public void add(final BookingEntity bookingEntity) {
        this.allCount++;

        if (bookingEntity.isAttended()) {
            this.attendedCount++;
        }

        if (BookingStatus.CANCELED.equals(bookingEntity.getStatus())) {
            this.canceledCount++;
        }
    }
}
