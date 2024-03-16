package com.valletta.pass.job.notification;

import com.valletta.pass.adapter.message.KakaoTalkMessageAdapter;
import com.valletta.pass.config.KakaoTalkMessageConfig;
import com.valletta.pass.config.TestBatchConfig;
import com.valletta.pass.repository.booking.BookingEntity;
import com.valletta.pass.repository.booking.BookingRepository;
import com.valletta.pass.repository.booking.BookingStatus;
import com.valletta.pass.repository.pass.PassEntity;
import com.valletta.pass.repository.pass.PassRepository;
import com.valletta.pass.repository.pass.PassStatus;
import com.valletta.pass.repository.user.UserEntity;
import com.valletta.pass.repository.user.UserRepository;
import com.valletta.pass.repository.user.UserStatus;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions.*;
import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;
import org.testcontainers.shaded.org.bouncycastle.asn1.x509.UserNotice;

@Slf4j
@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {
    SendNotificationBeforeClassJobConfig.class,
    TestBatchConfig.class,
    SendNotificationItemWriter.class,
    KakaoTalkMessageConfig.class,
    KakaoTalkMessageAdapter.class
})
public class SendNotificationBeforeClassJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PassRepository passRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_addNotificationStep() throws Exception {
        // given
        addBookingEntity();
    }

    private void addBookingEntity() {
        final LocalDateTime now = LocalDateTime.now();
        final String userId = "A100" + RandomStringUtils.randomNumeric(4);

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(userId);
        userEntity.setUserName("김철수");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setPhone("010-3002-3040");
        userEntity.setMeta(Map.of("uuid", "abcd1234"));
        userRepository.save(userEntity);

        PassEntity passEntity = new PassEntity();
        passEntity.setPackageSeq(1);
        passEntity.setUserId(userId);
        passEntity.setStatus(PassStatus.PROGRESSED);
        passEntity.setRemainingCount(10);
        passEntity.setStartedAt(now.minusDays(60));
        passEntity.setEndedAt(now.minusDays(1));
        passRepository.save(passEntity);
    }
}