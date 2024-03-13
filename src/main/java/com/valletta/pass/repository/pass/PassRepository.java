package com.valletta.pass.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {
}
