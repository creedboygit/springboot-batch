package com.valletta.pass.service.user;

import com.valletta.pass.repository.user.UserGroupMappingEntity;
import com.valletta.pass.repository.user.UserGroupMappingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGroupMappingService {

    final private UserGroupMappingRepository userGroupMappingRepository;

    public List<String> getAllUserGroupIds() {
        return userGroupMappingRepository.findDistinctUserGroupIds();
    }
}
