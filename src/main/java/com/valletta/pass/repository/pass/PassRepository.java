package com.valletta.pass.repository.pass;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update PassEntity p" +
        " set p.remainingCount = :remainingCount, " +
        " p.modifiedAt = CURRENT_TIMESTAMP " +
        " where p.passSeq = :passSeq")
    int updateRemainingCount(Integer passSeq, Integer remainingCount);

    @Query(value = "select p from PassEntity p " +
        "join fetch p.packageEntity " +
        "where p.userId = :userId " +
        "order by p.endedAt desc nulls first")
    List<PassEntity> findByUserId(@Param("userId") String userId);
}
