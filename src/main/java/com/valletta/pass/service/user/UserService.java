package com.valletta.pass.service.user;

import com.valletta.pass.repository.user.UserEntity;
import com.valletta.pass.repository.user.UserModelMapper;
import com.valletta.pass.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String userId) {
        // userId를 조건으로 사용자 정보를 조회합니다. 프로필에 노출할 사용자의 이름이 필요합니다.
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserModelMapper.INSTANCE.toUser(userEntity);
    }
}
