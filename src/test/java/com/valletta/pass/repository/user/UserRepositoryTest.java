package com.valletta.pass.repository.user;

import com.valletta.pass.config.TestBatchConfig;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

@DataJpaTest
@ActiveProfiles("tc")
//@ActiveProfiles("test")
//@SpringBootTest
@Testcontainers
@ContextConfiguration(classes = {TestBatchConfig.class}) // BaseEntity의 createAt, modifiedAt을 위함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_save() {
        // given
        UserEntity userEntity = new UserEntity();
        final String userId = "C100" + RandomStringUtils.randomNumeric(4);
        userEntity.setUserId(userId);
        userEntity.setUserName("김파랑");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setPhone("01039392929");
        userEntity.setMeta(Map.of("uuid", "abcd1234"));

        // when
        userRepository.save(userEntity);

        // then
        final Optional<UserEntity> optionalUserEntity = Optional.ofNullable(userRepository.findByUserId(userId));
//        final UserEntity optionalUserEntity = userRepository.findByUserId(userId);
        Assertions.assertTrue(optionalUserEntity.isPresent());
    }
}
