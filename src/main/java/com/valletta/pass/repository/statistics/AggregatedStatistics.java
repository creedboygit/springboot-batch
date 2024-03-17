package com.valletta.pass.repository.statistics;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AggregatedStatistics {
    private LocalDateTime statisticsAt; // 일 단위
    private long allCount;
    private long attendedCount;
    private long canceledCount;

    public void merge(final AggregatedStatistics statistics) {
        this.allCount += statistics.getAllCount();
        this.attendedCount += statistics.getAttendedCount();
        this.canceledCount += statistics.getCanceledCount();
    }
}
