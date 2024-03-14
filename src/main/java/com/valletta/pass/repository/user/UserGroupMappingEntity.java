package com.valletta.pass.repository.user;

import com.valletta.pass.repository.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_group_mapping")
@IdClass(UserGroupMappingId.class)
public class UserGroupMappingEntity extends BaseEntity {

    @Id
    @Column(name = "user_group_id")
    private String userGroupId;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_group_name")
    private String userGroupName;
    private String description;
}
