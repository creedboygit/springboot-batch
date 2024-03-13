package com.valletta.pass.repository.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {

    @Id
    private String userId;
}
