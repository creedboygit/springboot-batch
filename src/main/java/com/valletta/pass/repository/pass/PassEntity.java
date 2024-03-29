package com.valletta.pass.repository.pass;

import com.valletta.pass.repository.BaseEntity;
import com.valletta.pass.repository.packaze.PackageEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pass")
public class PassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
    @Column(name = "pass_seq")
    private Integer passSeq;

    @Column(name = "package_seq")
    private Integer packageSeq;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    private PassStatus status;

    @Column(name = "remaining_count")
    private Integer remainingCount;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "packageSeq", insertable = false, updatable = false)
    @JoinColumn(name = "package_seq", insertable = false, updatable = false)
    private PackageEntity packageEntity;

}
