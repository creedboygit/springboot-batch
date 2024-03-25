package com.valletta.pass.job.pass;

import com.valletta.pass.repository.pass.BulkPassEntity;
import com.valletta.pass.repository.pass.BulkPassRepository;
import com.valletta.pass.repository.pass.BulkPassStatus;
import com.valletta.pass.repository.pass.PassEntity;
import com.valletta.pass.repository.pass.PassRepository;
import com.valletta.pass.repository.user.UserGroupMappingEntity;
import com.valletta.pass.repository.user.UserGroupMappingRepository;
import com.valletta.pass.service.pass.PassModelMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@StepScope
@Component
public class AddPassesTasklet implements Tasklet {

    //    @Value("#{jobParameters[requestDate]}")
    private String requestDate;

    private final PassRepository passRepository;
    private final BulkPassRepository bulkPassRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;

    @Override
//    @StepScope
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext chunkContext) {

//        JobParameters jobParameters = contribution.getStepExecution().getJobParameters();
//        String name = jobParameters.getString("name");
//        String requestDate1 = jobParameters.getString("requestDate1");
//        String requestDate2 = requestDate;
//
//        Long seq = jobParameters.getLong("seq");
//        Date date = jobParameters.getDate("date");
//
//        log.info("#### name: {}", name);
//        log.info("#### requestDate1: {}", requestDate1);
//        log.info("#### requestDate2: {}", requestDate);
//        log.info("#### seq: {}", seq);
//        log.info("#### date: {}", date);

        log.info("########## excute: 실행됨");
        // 이용권 시작 일시 1일 전 user group 내 각 사용자에게 이용권을 추가해 줍니다.
        final LocalDateTime startedAt = LocalDateTime.now().minusDays(1);
        final List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findByStatusAndStartedAtGreaterThan(BulkPassStatus.READY, startedAt);

        int count = 0;
        for (BulkPassEntity bulkPassEntity : bulkPassEntities) {
            // user group에 속한 userId들을 조회합니다.
            List<String> userIds = userGroupMappingRepository.findByUserGroupId(bulkPassEntity.getUserGroupId())
                .stream().map(UserGroupMappingEntity::getUserId).toList();

            // 각 userId로 이용권을 추가합니다.
            count += addPasses(bulkPassEntity, userIds);

            // pass 추가 이후 상태를 COMPLETED로 업데이트합니다.
            bulkPassEntity.setStatus(BulkPassStatus.COMPLETED);
        }

        log.info("AddPassesTasklet - execute: 이용권 {}건 추가 완료, startedAt={}", count, startedAt);
        return RepeatStatus.FINISHED;
    }

    /**
     * bulkPass의 정보로 pass 데이터를 생성합니다.
     */
    private int addPasses(BulkPassEntity bulkPassEntity, List<String> userIds) {
        List<PassEntity> passEntities = new ArrayList<>();

        for (String userId : userIds) {
            PassEntity passEntity = PassModelMapper.INSTANCE.toPassEntity(bulkPassEntity, userId);
            passEntities.add(passEntity);
        }

        return passRepository.saveAll(passEntities).size();
    }
}
