package com.valletta.pass.service.pass;

import com.valletta.pass.controller.admin.BulkPassRequest;
import com.valletta.pass.repository.packaze.PackageEntity;
import com.valletta.pass.repository.packaze.PackageRepository;
import com.valletta.pass.repository.pass.BulkPassEntity;
import com.valletta.pass.repository.pass.BulkPassRepository;
import com.valletta.pass.repository.pass.BulkPassStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BulkPassService {

    private final BulkPassRepository bulkPassRepository;

    private final PackageRepository packageRepository;

    public List<BulkPass> getAllBulkPasses() {
        // startedAt 역순으로 모든 bulkPass를 조회합니다.
        List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findAllOrderByStartedAtDesc();
        return BulkPassModelMapper.INSTANCE.map(bulkPassEntities);
    }

    public void addBulkPass(BulkPassRequest bulkPassRequest) {

        log.debug("# bulkPassRequest: {}", bulkPassRequest.toString());

        // bulkPassRequest를 기반으로 passEntity를 생성하여 DB에 저장합니다.
        PackageEntity packageEntity = packageRepository.findById(bulkPassRequest.getPackageSeq()).orElseThrow(() -> new IllegalArgumentException("패키지가 없습니다."));

        BulkPassEntity bulkPassEntity = BulkPassModelMapper.INSTANCE.map(bulkPassRequest);
        bulkPassEntity.setStatus(BulkPassStatus.READY);
        bulkPassEntity.setCount(packageEntity.getCount());
        bulkPassEntity.setEndedAtFromPeriod(packageEntity.getPeriod());

        bulkPassRepository.save(bulkPassEntity);
    }
}
