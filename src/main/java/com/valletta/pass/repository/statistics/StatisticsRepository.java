package com.valletta.pass.repository.statistics;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Integer> {

    @Query(value = "select new com.valletta.pass.repository.statistics.AggregatedStatistics(s.statisticsAt, sum(s.allCount), sum(s.attendedCount), sum(s.canceledCount)) " +
        "from StatisticsEntity s " +
        " where s.statisticsAt between :from and :to " +
        " group by s.statisticsAt")
    List<AggregatedStatistics> findByStatisticsAtBetweenAndGroupBy(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
