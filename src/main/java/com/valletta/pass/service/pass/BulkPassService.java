package com.valletta.pass.service.pass;

import com.valletta.pass.repository.pass.BulkPassEntity;
import com.valletta.pass.repository.pass.BulkPassRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BulkPassService {

    private final BulkPassRepository bulkPassRepository;

    public Object getAllBulkPasses() {
        List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findAllOrderByStartedAtDesc();
        return BulkPassModelMapper.INSTANCE.map(bulkPassEntities);
    }
}
