package com.valletta.pass.repository.user;

import java.util.List;
import javax.imageio.ImageTranscoder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMappingEntity, Integer> {

    List<UserGroupMappingEntity> findByUserGrouId(String userGroupId);
}
