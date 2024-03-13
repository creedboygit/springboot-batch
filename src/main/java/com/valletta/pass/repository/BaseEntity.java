package com.valletta.pass.repository;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 공통 매핑 정보를 가진 Entity
 */
@MappedSuperclass // 상속받은 entity에서 아래 필드들을 컬럼으로 사용할 수 있습니다.
@EntityListeners(AuditingEntityListener.class) // Auditing 정보를 캡처하는 Listener입니다.
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
