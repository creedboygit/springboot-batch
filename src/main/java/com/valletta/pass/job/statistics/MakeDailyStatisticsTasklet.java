package com.valletta.pass.job.statistics;

import com.valletta.pass.repository.statistics.AggregatedStatistics;
import com.valletta.pass.repository.statistics.StatisticsRepository;
import com.valletta.pass.util.CustomCSVWriter;
import com.valletta.pass.util.LocalDateTimeUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@StepScope
public class MakeDailyStatisticsTasklet implements Tasklet {

    @Value("#{jobParameters[from]}")
    private String fromString;

    @Value("#{jobParameters[to]}")
    private String toString;

    private final StatisticsRepository statisticsRepository;

    @Override
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) throws Exception {

        final LocalDateTime from = LocalDateTimeUtils.parse(fromString);
        final LocalDateTime to = LocalDateTimeUtils.parse(toString);

        final List<AggregatedStatistics> statisticsList = statisticsRepository.findByStatisticsAtBetweenAndGroupBy(from, to);

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"statisticsAt", "allCount", "attendedCount", "canceledCount"});

        for (AggregatedStatistics statistics : statisticsList) {
            data.add(new String[]{
                LocalDateTimeUtils.format(statistics.getStatisticsAt()),
                String.valueOf(statistics.getAllCount()),
                String.valueOf(statistics.getAttendedCount()),
                String.valueOf(statistics.getCanceledCount())
            });
        }

        CustomCSVWriter.write("daily_statistics_" + LocalDateTimeUtils.format(from, LocalDateTimeUtils.YYYYM_MM_DD) + ".csv", data);
        return RepeatStatus.FINISHED;
    }
}
