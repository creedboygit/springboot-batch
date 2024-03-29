package com.valletta.pass.job.pass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.valletta.pass.config.TestBatchConfig;
import com.valletta.pass.repository.pass.BulkPassEntity;
import com.valletta.pass.repository.pass.BulkPassRepository;
import com.valletta.pass.repository.pass.BulkPassStatus;
import com.valletta.pass.repository.user.UserGroupMappingEntity;
import com.valletta.pass.repository.user.UserGroupMappingRepository;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

@Slf4j
@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
//@RequiredArgsConstructor
@ContextConfiguration(classes = {AddPassesJobConfig.class, TestBatchConfig.class, AddPassesTasklet.class})
public class AddPassesJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private BulkPassRepository bulkPassRepository;

    @Autowired
    private UserGroupMappingRepository userGroupMappingRepository;

    @Test
    public void test_addPassesJob() throws Exception {
        // given
        addBuklPassEntity();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance jobInstance = jobExecution.getJobInstance();

        // then
        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertEquals("addPassesJob", jobInstance.getJobName());
    }

    private void addBuklPassEntity() {
        final LocalDateTime now = LocalDateTime.now();
        final String userGroupId = RandomStringUtils.randomAlphabetic(6);
        final String userId = "A100" + RandomStringUtils.randomNumeric(4);

        log.info("# userGroupId: {}", userGroupId);
        log.info("# userId: {}", userId);

        BulkPassEntity bulkPassEntity = new BulkPassEntity();
        bulkPassEntity.setPackageSeq(1);
        bulkPassEntity.setUserGroupId(userGroupId);
        bulkPassEntity.setStatus(BulkPassStatus.READY);
        bulkPassEntity.setCount(10);
        bulkPassEntity.setStartedAt(now);
        bulkPassEntity.setEndedAt(now.plusDays(60));

        bulkPassRepository.save(bulkPassEntity);

        UserGroupMappingEntity userGroupMappingEntity = new UserGroupMappingEntity();
        userGroupMappingEntity.setUserGroupId(userGroupId);
        userGroupMappingEntity.setUserId(userId);
        userGroupMappingEntity.setUserGroupName("그룹");
        userGroupMappingEntity.setDescription("그룹 설명");

        userGroupMappingRepository.save(userGroupMappingEntity);
    }
}
