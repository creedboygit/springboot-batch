package com.valletta.pass.service.statistics;

import com.valletta.pass.repository.statistics.AggregatedStatistics;
import com.valletta.pass.repository.statistics.StatisticsRepository;
import com.valletta.pass.util.LocalDateTimeUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public ChartData makeChartData(final LocalDateTime to) {
        final LocalDateTime from = to.minusDays(10);

        final List<AggregatedStatistics> aggregatedStatistics = statisticsRepository.findByStatisticsAtBetweenAndGroupBy(from, to);
        List<String> labels = new ArrayList<>();
        List<Long> attendedCounts = new ArrayList<>();
        List<Long> canceledCounts = new ArrayList<>();

        for (AggregatedStatistics statistics : aggregatedStatistics) {
            labels.add(LocalDateTimeUtils.format(statistics.getStatisticsAt(), LocalDateTimeUtils.MM_DD));
            attendedCounts.add(statistics.getAttendedCount());
            canceledCounts.add(statistics.getCanceledCount());
        }

        return new ChartData(labels, attendedCounts, canceledCounts);
    }

}
