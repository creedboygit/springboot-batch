package com.valletta.pass.repository.user;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGroupMappingId implements Serializable {
//    @Column(name = "user_group_id")
    private String userGroupId;

//    @Column(name = "user_id")
    private String userId;
}
