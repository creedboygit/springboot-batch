package com.valletta.pass.repository.pass;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PassEntity {

    @Id
    private String passSeq;
}
