package com.valletta.pass.repository.user;

import com.valletta.pass.repository.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@TypeDef(name = "json", typeClass = JsonStringType.class) // json 타입 정의
public class UserEntity extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String phone;

    @Type(type = "json")
    private Map<String, Object> meta;

    public String getUuid() {
        String uuid = null;
        if (meta.containsKey("uuid")) {
            uuid = String.valueOf(meta.get("uuid"));
        }
        return uuid;
    }
}
