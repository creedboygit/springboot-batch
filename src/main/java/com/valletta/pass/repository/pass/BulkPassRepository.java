package com.valletta.pass.repository.pass;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BulkPassRepository extends JpaRepository<BulkPassEntity, Integer> {

    // WHERE status = :status AND startedAt > :startedAt
    List<BulkPassEntity> findByStatusAndStartedAtGreaterThan(BulkPassStatus status, LocalDateTime startedAt);

    @Query(value = "select b from BulkPassEntity b "
        + "order by b.startedAt desc")
    List<BulkPassEntity> findAllOrderByStartedAtDesc();
}
