package com.valletta.pass.service.packaze;

import com.valletta.pass.repository.packaze.PackageEntity;
import com.valletta.pass.repository.packaze.PackageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PackageService {

    final private PackageRepository packageRepository;

    public List<Package> getAllPackage() {
        List<PackageEntity> bulkPassEntities = packageRepository.findAllByOrderByPackageName();
        return PackageModelMapper.INSTANCE.map(bulkPassEntities);
    }
}
