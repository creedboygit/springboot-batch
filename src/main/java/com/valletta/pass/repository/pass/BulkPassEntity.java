package com.valletta.pass.repository.pass;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bulk_pass")
public class BulkPassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bulk_pass_seq")
    private Integer bulkPassSeq;

    @Column(name = "package_seq")
    private Integer packageSeq;

    @Column(name = "user_group_id")
    private String userGroupId;

    @Enumerated(EnumType.STRING)
    private BulkPassStatus status;

    private Integer count;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    public void setEndedAtFromPeriod(Integer period) {
        if (period == null) {
            return;
        }
        this.endedAt = this.startedAt.plusDays(period);
    }
}
