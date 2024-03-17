package com.valletta.pass.repository.booking;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update BookingEntity b set " +
        "b.usedPass = :usedPass, " +
        "b.modifiedAt = CURRENT_TIMESTAMP " +
        "where b.passSeq = :passSeq")
    int updateUsedPass(Integer passSeq, boolean usedPass);
}
