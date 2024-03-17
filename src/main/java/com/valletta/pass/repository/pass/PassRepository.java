package com.valletta.pass.repository.pass;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update PassEntity p" +
        " set p.remainingCount = :remainingCount, " +
        " p.modifiedAt = CURRENT_TIMESTAMP " +
        " where p.passSeq = :passSeq")
    int updateRemainingCount(Integer passSeq, Integer remainingCount);
}
