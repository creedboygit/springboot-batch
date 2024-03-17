package com.valletta.pass.repository.pass;

import com.valletta.pass.repository.BaseEntity;
import com.valletta.pass.repository.packaze.PackageEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "packageSeq", insertable = false, updatable = false)
    @JoinColumn(name = "package_seq", insertable = false, updatable = false)
    private PackageEntity packageEntity;

}
