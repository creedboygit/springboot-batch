package com.valletta.pass.service.packaze;

import com.valletta.pass.repository.packaze.PackageEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PackageModelMapper {

    PackageModelMapper INSTANCE = Mappers.getMapper(PackageModelMapper.class);

    List<Package> map(List<PackageEntity> packageEntities);
}
