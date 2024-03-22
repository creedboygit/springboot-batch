package com.valletta.pass.service.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.valletta.pass.repository.statistics.AggregatedStatistics;
import com.valletta.pass.repository.statistics.StatisticsRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest2 {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    public void test_makeChartData() {

        // given
        final LocalDateTime to = LocalDateTime.of(2024, 3, 24, 0, 0);

        List<AggregatedStatistics> statisticsList = List.of(
            new AggregatedStatistics(to.minusDays(1), 15, 10, 5),
            new AggregatedStatistics(to, 20, 3, 40)
        );

        // when
        when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))).thenReturn(statisticsList);
        final ChartData chartData = statisticsService.makeChartData(to);

        // then
        verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));

        assertNotNull(chartData);
        assertEquals(new ArrayList<>(List.of("03-23", "03-24")), chartData.getLabels());
        assertEquals(new ArrayList<>(List.of(10L, 3L)), chartData.getAttendedCounts());
        assertEquals(new ArrayList<>(List.of(5L, 40L)), chartData.getCanceledCounts());
    }
}
