package com.valletta.pass.service.pass;

import com.valletta.pass.repository.pass.PassEntity;
import com.valletta.pass.repository.pass.PassModelMapper;
import com.valletta.pass.repository.pass.PassRepository;
import com.valletta.pass.service.pass.Pass;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PassService {

    private final PassRepository passRepository;

    public List<Pass> getPasses(String userId) {
        // userId를 조건으로 pass를 조회합니다. 이 때 packageSeq에 맞는 package 정보도 필요합니다.
        final List<PassEntity> passEntities = passRepository.findByUserId(userId);
        return PassModelMapper.INSTANCE.map(passEntities);
    }
}
