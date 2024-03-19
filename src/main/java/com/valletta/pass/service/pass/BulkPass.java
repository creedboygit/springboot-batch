package com.valletta.pass.service.pass;

import com.valletta.pass.repository.pass.BulkPassStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BulkPass {

    private Integer bulkPassSeq;
    private String userGroupId;
    private Integer count;
    private BulkPassStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
